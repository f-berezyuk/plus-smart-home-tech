package ru.yandex.practicum.service;

import ru.yandex.practicum.model.SensorEvent;
import ru.yandex.practicum.model.SensorEventType;

public interface EventHandler {
    SensorEventType getEventType();

    void handle(SensorEvent event);
}
