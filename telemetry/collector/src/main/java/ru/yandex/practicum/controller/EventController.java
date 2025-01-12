package ru.yandex.practicum.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.yandex.practicum.model.SensorEvent;
import ru.yandex.practicum.model.SensorEventType;
import ru.yandex.practicum.service.EventHandler;


@RestController
@RequestMapping(path = "/events", consumes = MediaType.APPLICATION_JSON_VALUE)
public class EventController {
    Map<SensorEventType, EventHandler> handlers;

    public EventController(List<EventHandler> eventHandlers) {
        handlers = eventHandlers.stream().collect(Collectors.toMap(EventHandler::getEventType,
                eventHandler -> eventHandler));
    }

    @PostMapping("/sensors")
    public void collectSensorEvent(@Valid @RequestBody SensorEvent event) {
        if (!handlers.containsKey(event.getType())) {
            throw new IllegalArgumentException("Handlers for event type: [" + event.getType() + "] not found.");
        }

        handlers.get(event.getType()).handle(event);
    }
}