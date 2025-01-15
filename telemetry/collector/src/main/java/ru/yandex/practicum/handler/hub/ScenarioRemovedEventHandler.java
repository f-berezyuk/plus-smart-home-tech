package ru.yandex.practicum.handler.hub;

import org.springframework.stereotype.Service;

import ru.yandex.practicum.handler.HubEventHandler;
import ru.yandex.practicum.kafka.KafkaConfig;
import ru.yandex.practicum.kafka.telemetry.event.ScenarioRemovedEventAvro;
import ru.yandex.practicum.model.Event;
import ru.yandex.practicum.model.hub.HubEventType;
import ru.yandex.practicum.model.hub.ScenarioRemovedEvent;

@Service
public class ScenarioRemovedEventHandler extends HubEventHandler<ScenarioRemovedEventAvro> {
    public ScenarioRemovedEventHandler(KafkaConfig.KafkaEventProducer producer, KafkaConfig kafkaTopics) {
        super(producer, kafkaTopics);
    }

    @Override
    protected ScenarioRemovedEventAvro mapToAvro(Event<HubEventType> event) {
        var scenarioRemovedEvent = (ScenarioRemovedEvent) event;

        return new ScenarioRemovedEventAvro(scenarioRemovedEvent.getName());

    }

    @Override
    public HubEventType getEventType() {
        return HubEventType.SCENARIO_REMOVED;
    }
}
