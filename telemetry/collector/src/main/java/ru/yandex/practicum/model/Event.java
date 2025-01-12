package ru.yandex.practicum.model;

import java.time.Instant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("rawtypes")
@Getter
@Setter
@ToString
public abstract class Event<E extends Enum> {
    @NotBlank
    private String hubId;

    private Instant timestamp = Instant.now();

    @NotNull
    public abstract E getType();
}
