package ru.yandex.practicum.handler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecordBase;

import ru.yandex.practicum.kafka.KafkaConfig;
import ru.yandex.practicum.kafka.TopicType;
import ru.yandex.practicum.model.Event;
import ru.yandex.practicum.model.hub.HubEventType;

@Slf4j
@AllArgsConstructor
public abstract class HubEventHandler<T extends SpecificRecordBase> implements EventHandler<HubEventType> {
    protected final KafkaConfig.KafkaEventProducer producer;
    protected final KafkaConfig kafkaConfig;

    protected abstract T mapToAvro(Event<HubEventType> event);

    @Override
    public void handle(Event<HubEventType> event) {
        T avroEvent = mapToAvro(event);
        var topic = kafkaConfig.getProducer().getTopics().get(getTopicType());
        log.debug("Publish hub event {} to topic {}", getEventType(), topic);
        producer.send(topic, event.getHubId(), avroEvent);
    }

    @Override
    public TopicType getTopicType() {
        return TopicType.HUBS_EVENTS;
    }
}
