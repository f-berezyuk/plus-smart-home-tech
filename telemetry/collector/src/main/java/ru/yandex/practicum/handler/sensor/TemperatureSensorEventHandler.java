package ru.yandex.practicum.handler.sensor;

import org.springframework.stereotype.Service;

import ru.yandex.practicum.handler.SensorEventHandler;
import ru.yandex.practicum.kafka.KafkaConfig;
import ru.yandex.practicum.kafka.telemetry.event.TemperatureSensorAvro;
import ru.yandex.practicum.model.Event;
import ru.yandex.practicum.model.sensor.SensorEventType;
import ru.yandex.practicum.model.sensor.TemperatureSensorEvent;

@Service
public class TemperatureSensorEventHandler extends SensorEventHandler<TemperatureSensorAvro> {
    public TemperatureSensorEventHandler(KafkaConfig.KafkaEventProducer producer, KafkaConfig kafkaTopics) {
        super(producer, kafkaTopics);
    }

    @Override
    protected TemperatureSensorAvro mapToAvro(Event<SensorEventType> event) {
        var tempEvent = (TemperatureSensorEvent) event;

        return new TemperatureSensorAvro(
                tempEvent.getId(),
                tempEvent.getHubId(),
                tempEvent.getTimestamp(),
                tempEvent.getTemperatureC(),
                tempEvent.getTemperatureF()
        );
    }

    @Override
    public SensorEventType getEventType() {
        return SensorEventType.TEMPERATURE_SENSOR_EVENT;
    }
}
