package ru.yandex.practicum.handler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecordBase;

import ru.yandex.practicum.kafka.KafkaConfig;
import ru.yandex.practicum.kafka.TopicType;
import ru.yandex.practicum.model.Event;
import ru.yandex.practicum.model.sensor.SensorEvent;
import ru.yandex.practicum.model.sensor.SensorEventType;

@Slf4j
@AllArgsConstructor
public abstract class SensorEventHandler<T extends SpecificRecordBase> implements EventHandler<SensorEventType> {
    protected final KafkaConfig.KafkaEventProducer producer;
    protected final KafkaConfig kafkaConfig;

    protected abstract T mapToAvro(Event<SensorEventType> event);

    @Override
    public void handle(Event<SensorEventType> event) {
        T avroEvent = mapToAvro(event);
        var sensorEvent = (SensorEvent) event;
        var topic = kafkaConfig.getProducer().getTopics().get(getTopicType());
        log.debug("Publish sensor event {} to topic {}", getEventType(), topic);
        producer.send(topic, sensorEvent.getId(), avroEvent);
    }

    @Override
    public TopicType getTopicType() {
        return TopicType.SENSORS_EVENTS;
    }
}

