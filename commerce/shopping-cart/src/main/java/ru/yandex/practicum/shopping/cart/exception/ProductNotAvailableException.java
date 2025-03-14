package ru.yandex.practicum.shopping.cart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductNotAvailableException extends RuntimeException {
    public ProductNotAvailableException(String message) {
        super(message);
    }
}
