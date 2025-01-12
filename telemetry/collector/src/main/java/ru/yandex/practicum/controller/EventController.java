package ru.yandex.practicum.controller;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.yandex.practicum.handler.EventHandler;
import ru.yandex.practicum.model.hub.HubEvent;
import ru.yandex.practicum.model.hub.HubEventType;
import ru.yandex.practicum.model.sensor.SensorEvent;
import ru.yandex.practicum.model.sensor.SensorEventType;

@RestController
@RequestMapping(path = "/events", consumes = MediaType.APPLICATION_JSON_VALUE)
public class EventController {
    private final Map<SensorEventType, EventHandler<SensorEventType>> sensorHandlers;
    private final Map<HubEventType, EventHandler<HubEventType>> hubHandlers;

    public EventController(List<EventHandler<SensorEventType>> sensorEventHandlers,
                           List<EventHandler<HubEventType>> hubEventHandlers) {
        hubHandlers = hubEventHandlers.stream()
                .collect(Collectors.toMap(EventHandler<HubEventType>::getEventType, Function.identity()));
        sensorHandlers = sensorEventHandlers.stream()
                .collect(Collectors.toMap(EventHandler<SensorEventType>::getEventType,
                        Function.identity()));
    }

    @PostMapping("/sensors")
    public void collectSensorEvent(@Valid @RequestBody SensorEvent event) {
        if (!sensorHandlers.containsKey(event.getType())) {
            throw new IllegalArgumentException("Sensor handlers for event type: [" + event.getType() + "] not found.");
        }

        sensorHandlers.get(event.getType()).handle(event);
    }

    @PostMapping("/hubs")
    public void collectHubEvent(@Valid @RequestBody HubEvent event) {
        if (!hubHandlers.containsKey(event.getType())) {
            throw new IllegalArgumentException("Hub handlers for event type: [" + event.getType() + "] not found.");
        }

        hubHandlers.get(event.getType()).handle(event);
    }
}