package ru.yandex.practicum.handler.sensor;

import org.springframework.stereotype.Service;

import ru.yandex.practicum.handler.SensorEventHandler;
import ru.yandex.practicum.kafka.KafkaConfig;
import ru.yandex.practicum.kafka.telemetry.event.LightSensorAvro;
import ru.yandex.practicum.model.Event;
import ru.yandex.practicum.model.sensor.LightSensorEvent;
import ru.yandex.practicum.model.sensor.SensorEventType;

@Service
public class LightSensorEventHandler extends SensorEventHandler<LightSensorAvro> {
    public LightSensorEventHandler(KafkaConfig.KafkaEventProducer producer, KafkaConfig kafkaConfig) {
        super(producer, kafkaConfig);
    }

    @Override
    protected LightSensorAvro mapToAvro(Event<SensorEventType> event) {
        var lightEvent = (LightSensorEvent) event;
        return LightSensorAvro.newBuilder()
                .setLinkQuality(lightEvent.getLinkQuality())
                .setLuminosity(lightEvent.getLuminosity())
                .build();
    }

    @Override
    public SensorEventType getEventType() {
        return SensorEventType.LIGHT_SENSOR_EVENT;
    }
}
