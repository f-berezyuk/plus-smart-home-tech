package ru.yandex.practicum.handler.sensor;

import org.springframework.stereotype.Service;

import ru.yandex.practicum.handler.SensorEventHandler;
import ru.yandex.practicum.kafka.KafkaConfig;
import ru.yandex.practicum.kafka.telemetry.event.MotionSensorAvro;
import ru.yandex.practicum.model.Event;
import ru.yandex.practicum.model.sensor.MotionSensorEvent;
import ru.yandex.practicum.model.sensor.SensorEventType;

@Service
public class MotionSensorEventHandler extends SensorEventHandler<MotionSensorAvro> {
    public MotionSensorEventHandler(KafkaConfig.KafkaEventProducer producer, KafkaConfig kafkaTopics) {
        super(producer, kafkaTopics);
    }

    @Override
    protected MotionSensorAvro mapToAvro(Event<SensorEventType> event) {
        var motionEvent = (MotionSensorEvent) event;

        return new MotionSensorAvro(
                motionEvent.getLinkQuality(),
                motionEvent.isMotion(),
                motionEvent.getVoltage()
        );
    }

    @Override
    public SensorEventType getEventType() {
        return SensorEventType.MOTION_SENSOR_EVENT;
    }
}
