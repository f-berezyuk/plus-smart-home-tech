package ru.yandex.practicum.handler.sensor;

import org.springframework.stereotype.Service;

import ru.yandex.practicum.handler.SensorEventHandler;
import ru.yandex.practicum.kafka.KafkaConfig;
import ru.yandex.practicum.kafka.telemetry.event.SwitchSensorAvro;
import ru.yandex.practicum.model.Event;
import ru.yandex.practicum.model.sensor.SensorEventType;
import ru.yandex.practicum.model.sensor.SwitchSensorEvent;

@Service
public class SwitchSensorEventHandler extends SensorEventHandler<SwitchSensorAvro> {

    public SwitchSensorEventHandler(KafkaConfig.KafkaEventProducer producer, KafkaConfig kafkaTopics) {
        super(producer, kafkaTopics);
    }

    @Override
    protected SwitchSensorAvro mapToAvro(Event<SensorEventType> event) {
        var switchEvent = (SwitchSensorEvent) event;

        return new SwitchSensorAvro(switchEvent.isState());
    }

    @Override
    public SensorEventType getEventType() {
        return SensorEventType.SWITCH_SENSOR_EVENT;
    }
}
