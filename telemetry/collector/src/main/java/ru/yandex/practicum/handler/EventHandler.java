package ru.yandex.practicum.handler;

import ru.yandex.practicum.kafka.TopicType;
import ru.yandex.practicum.model.Event;

@SuppressWarnings("rawtypes")
public interface EventHandler<E extends Enum> {
    E getEventType();

    void handle(Event<E> event);

    TopicType getTopicType();
}

