package ru.yandex.practicum.handler.hub;

import org.springframework.stereotype.Service;

import ru.yandex.practicum.handler.HubEventHandler;
import ru.yandex.practicum.kafka.KafkaConfig;
import ru.yandex.practicum.kafka.telemetry.event.DeviceRemovedEventAvro;
import ru.yandex.practicum.model.Event;
import ru.yandex.practicum.model.hub.DeviceRemovedEvent;
import ru.yandex.practicum.model.hub.HubEventType;

@Service
public class DeviceRemovedEventHandler extends HubEventHandler<DeviceRemovedEventAvro> {
    public DeviceRemovedEventHandler(KafkaConfig.KafkaEventProducer producer, KafkaConfig kafkaTopics) {
        super(producer, kafkaTopics);
    }

    @Override
    protected DeviceRemovedEventAvro mapToAvro(Event<HubEventType> event) {
        var deviceRemovedEvent = (DeviceRemovedEvent) event;
        return new DeviceRemovedEventAvro(deviceRemovedEvent.getId());
    }

    @Override
    public HubEventType getEventType() {
        return HubEventType.DEVICE_REMOVED;
    }
}
