package ru.yandex.practicum.handler.hub;

import org.springframework.stereotype.Service;

import ru.yandex.practicum.handler.HubEventHandler;
import ru.yandex.practicum.kafka.KafkaConfig;
import ru.yandex.practicum.kafka.telemetry.event.DeviceActionAvro;
import ru.yandex.practicum.kafka.telemetry.event.ScenarioAddedEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.ScenarioConditionAvro;
import ru.yandex.practicum.model.Event;
import ru.yandex.practicum.model.hub.DeviceAction;
import ru.yandex.practicum.model.hub.HubEventType;
import ru.yandex.practicum.model.hub.ScenarioAddedEvent;
import ru.yandex.practicum.model.hub.ScenarioCondition;

@Service
public class ScenarioAddedEventHandler extends HubEventHandler<ScenarioAddedEventAvro> {
    public ScenarioAddedEventHandler(KafkaConfig.KafkaEventProducer producer, KafkaConfig kafkaTopics) {
        super(producer, kafkaTopics);
    }

    @Override
    protected ScenarioAddedEventAvro mapToAvro(Event<HubEventType> event) {
        var scenarioEvent = (ScenarioAddedEvent) event;

        return new ScenarioAddedEventAvro(
                scenarioEvent.getName(),
                scenarioEvent.getConditions().stream().map(this::mapConditionToAvro).toList(),
                scenarioEvent.getActions().stream().map(this::mapActionToAvro).toList()
        );
    }

    @Override
    public HubEventType getEventType() {
        return HubEventType.SCENARIO_ADDED;
    }

    private ScenarioConditionAvro mapConditionToAvro(ScenarioCondition condition) {
        return new ScenarioConditionAvro(
                condition.getSensorId(),
                ru.yandex.practicum.kafka.telemetry.event.ConditionTypeAvro.valueOf(condition.getType().name()),
                ru.yandex.practicum.kafka.telemetry.event.ConditionOperationAvro.valueOf(condition.getOperation().name()),
                condition.getValue()
        );
    }

    private DeviceActionAvro mapActionToAvro(DeviceAction action) {
        return new DeviceActionAvro(
                action.getSensorId(),
                ru.yandex.practicum.kafka.telemetry.event.ActionTypeAvro.valueOf(action.getType().name()),
                action.getValue()
        );
    }
}
