package ru.yandex.practicum.service;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import ru.yandex.practicum.model.SensorEvent;
import ru.yandex.practicum.model.SensorEventType;

@Getter
@Setter
@ToString(callSuper = true)
public class LightSensorEventHandler extends SensorEvent {
    private int linkQuality;
    private int luminosity;

    @Override
    public SensorEventType getType() {
        return SensorEventType.LIGHT_SENSOR_EVENT;
    }
}
