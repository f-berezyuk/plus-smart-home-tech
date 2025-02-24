package ru.yandex.practicum;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.errors.WakeupException;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import ru.yandex.practicum.kafka.config.KafkaConfig;
import ru.yandex.practicum.kafka.telemetry.event.SensorEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.SensorStateAvro;
import ru.yandex.practicum.kafka.telemetry.event.SensorsSnapshotAvro;

@Slf4j
@Component
@EnableConfigurationProperties
public class AggregationStarter {
    private final KafkaConfig kafkaConfig;
    private final KafkaProducer<String, SensorsSnapshotAvro> producer;
    private final KafkaConsumer<String, SensorEventAvro> consumer;
    private final SnapshotsRepository snapshotsRepository;

    public AggregationStarter(KafkaConfig kafkaConfig) {
        log.info(kafkaConfig.getTopics().toString());
        log.info(kafkaConfig.getConsumer().toString());
        log.info(kafkaConfig.getProducer().toString());
        this.kafkaConfig = kafkaConfig;
        producer = new KafkaProducer<>(kafkaConfig.getProducer());
        consumer = new KafkaConsumer<>(kafkaConfig.getConsumer());
        snapshotsRepository = new SnapshotsRepository();
    }

    public void start() {
        try (consumer) {
            Runtime.getRuntime().addShutdownHook(new Thread(consumer::wakeup));
            consumer.subscribe(List.of(kafkaConfig.getTopics().get("telemetry-sensors")));

            //poll loop - цикл опроса
            while (true) {
                ConsumerRecords<String, SensorEventAvro> records =
                        consumer.poll(Duration.ofSeconds(5));
                for (ConsumerRecord<String, SensorEventAvro> record : records) {
                    SensorEventAvro event = record.value();
                    Optional<SensorsSnapshotAvro> updateSnapshot = updateState(event);
                    updateSnapshot.ifPresent(this::sendSnapshot);
                }

                consumer.commitAsync((offsets, exception) -> {
                    if (exception != null) {
                        log.warn("Commit processing error. Offsets: {}", offsets, exception);
                    }
                });
            }

        } catch (WakeupException ignored) {
            log.info("Analyzer. WakeupException");
        } catch (Exception e) {
            log.error("Aggregator. Error by handling events from sensors", e);
        } finally {
            log.info("Aggregator. Closing consumer.");
            consumer.close();
            log.info("Aggregator. Closing producer.");
            producer.close();
        }

    }

    private Optional<SensorsSnapshotAvro> updateState(SensorEventAvro event) {
        Optional<SensorsSnapshotAvro> oldSnapshot = snapshotsRepository.get(event.getHubId());

        if (oldSnapshot.isPresent()) {
            Optional<SensorStateAvro> oldEvent =
                    Optional.ofNullable(oldSnapshot.get().getSensorsState().get(event.getId()));
            if (oldEvent.isPresent() && oldEvent.get().getTimestamp() < (event.getTimestamp())) {
                SensorsSnapshotAvro newSnapshot = oldSnapshot.get();
                newSnapshot.setTimestamp(event.getTimestamp());
                newSnapshot.getSensorsState().put(event.getId(), newSnapshot.getSensorsState().get(event.getId()));
                return Optional.of(newSnapshot);
            } else {
                return Optional.empty();
            }

        } else {
            Map<String, SensorStateAvro> state = new HashMap<>();
            SensorStateAvro sensorStateAvro = new SensorStateAvro();
            sensorStateAvro.setTimestamp(event.getTimestamp());
            sensorStateAvro.setData(event);
            state.put(event.getId(), sensorStateAvro);
            return Optional.of(snapshotsRepository.update(event.getHubId(),
                    SensorsSnapshotAvro.newBuilder()
                            .setHubId(event.getHubId())
                            .setTimestamp(Instant.now().toEpochMilli())
                            .setSensorsState(state)
                            .build()));
        }
    }

    private void sendSnapshot(SensorsSnapshotAvro snapshot) {
        log.info("Sending snapshot for {}. SnapShot: {}", snapshot.getHubId(), snapshot);
        ProducerRecord<String, SensorsSnapshotAvro> snapshotRecord =
                new ProducerRecord<>(
                        kafkaConfig.getTopics().get("telemetry-snapshots"), null, snapshot.getHubId(), snapshot);
        log.info("Sending snapshot {}", snapshotRecord);
        try (producer) {
            producer.send(snapshotRecord);
            producer.flush();
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            log.info("Aggregator. Aggregator has been force-interrupted");
        }

    }

}