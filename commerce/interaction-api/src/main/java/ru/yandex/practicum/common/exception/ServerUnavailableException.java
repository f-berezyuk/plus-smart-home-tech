package ru.yandex.practicum.common.exception;

public class ServerUnavailableException extends RuntimeException {
    public ServerUnavailableException(String s) {
        super(s);
    }
}
