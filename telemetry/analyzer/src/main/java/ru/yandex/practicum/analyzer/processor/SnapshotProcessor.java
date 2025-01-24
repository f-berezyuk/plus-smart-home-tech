package ru.yandex.practicum.analyzer.processor;

import java.time.Duration;
import java.util.Collections;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Component;

import ru.yandex.practicum.analyzer.kafka.AnalyzerKafkaConfig;
import ru.yandex.practicum.analyzer.service.SnapshotService;
import ru.yandex.practicum.kafka.telemetry.event.SensorsSnapshotAvro;

@Slf4j
@Component
public class SnapshotProcessor implements Runnable {
    private static final Duration TIMEOUT = Duration.ofMillis(100);
    private final SnapshotService snapshotService;
    private final KafkaConsumer<String, SensorsSnapshotAvro> snapshotConsumer;

    public SnapshotProcessor(AnalyzerKafkaConfig kafkaConfig, SnapshotService snapshotService) {
        this.snapshotConsumer = new KafkaConsumer<>(kafkaConfig.getSnapshotConsumerProperties());
        this.snapshotService = snapshotService;
    }

    @Override
    public void run() {
        try (snapshotConsumer) {
            Runtime.getRuntime().addShutdownHook(new Thread(snapshotConsumer::wakeup));
            snapshotConsumer.subscribe(Collections.singletonList("telemetry.snapshots.v1"));

            while (!Thread.currentThread().isInterrupted()) {
                ConsumerRecords<String, SensorsSnapshotAvro> records = snapshotConsumer.poll(TIMEOUT);

                for (ConsumerRecord<String, SensorsSnapshotAvro> record : records) {
                    try {
                        readMessage(record);
                    } catch (Exception e) {
                        log.error("Error processing event: {}", record, e);
                    }
                }
            }
        } catch (Exception e) {
            log.error("Error in Kafka consumer", e);
        }
    }

    public void readMessage(ConsumerRecord<String, SensorsSnapshotAvro> record) {
        SensorsSnapshotAvro snapshot = record.value();
        log.info("Received snapshot: {}", snapshot);

        snapshotService.analyze(snapshot);
    }
}
