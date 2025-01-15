package ru.yandex.practicum.handler.sensor;

import org.springframework.stereotype.Service;

import ru.yandex.practicum.handler.SensorEventHandler;
import ru.yandex.practicum.kafka.KafkaConfig;
import ru.yandex.practicum.kafka.telemetry.event.ClimateSensorAvro;
import ru.yandex.practicum.model.Event;
import ru.yandex.practicum.model.sensor.ClimateSensorEvent;
import ru.yandex.practicum.model.sensor.SensorEventType;

@Service
public class ClimateSensorEventHandler extends SensorEventHandler<ClimateSensorAvro> {
    public ClimateSensorEventHandler(KafkaConfig.KafkaEventProducer producer, KafkaConfig kafkaTopics) {
        super(producer, kafkaTopics);
    }

    @Override
    protected ClimateSensorAvro mapToAvro(Event<SensorEventType> event) {
        var climateEvent = (ClimateSensorEvent) event;
        return new ClimateSensorAvro(
                climateEvent.getTemperatureC(),
                climateEvent.getHumidity(),
                climateEvent.getCo2Level()
        );
    }

    @Override
    public SensorEventType getEventType() {
        return SensorEventType.CLIMATE_SENSOR_EVENT;
    }
}
