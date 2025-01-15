package ru.yandex.practicum.handler.hub;

import org.springframework.stereotype.Service;

import ru.yandex.practicum.handler.HubEventHandler;
import ru.yandex.practicum.kafka.KafkaConfig;
import ru.yandex.practicum.kafka.telemetry.event.DeviceAddedEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.DeviceTypeAvro;
import ru.yandex.practicum.model.Event;
import ru.yandex.practicum.model.hub.DeviceAddedEvent;
import ru.yandex.practicum.model.hub.HubEventType;

@Service
public class DeviceAddedEventHandler extends HubEventHandler<DeviceAddedEventAvro> {
    public DeviceAddedEventHandler(KafkaConfig.KafkaEventProducer producer, KafkaConfig kafkaTopics) {
        super(producer, kafkaTopics);
    }

    @Override
    protected DeviceAddedEventAvro mapToAvro(Event<HubEventType> event) {
        var deviceAddedEvent = (DeviceAddedEvent) event;

        return new DeviceAddedEventAvro(
                deviceAddedEvent.getId(),
                DeviceTypeAvro.valueOf(deviceAddedEvent.getDeviceType().name())
        );
    }

    @Override
    public HubEventType getEventType() {
        return HubEventType.DEVICE_ADDED;
    }
}

