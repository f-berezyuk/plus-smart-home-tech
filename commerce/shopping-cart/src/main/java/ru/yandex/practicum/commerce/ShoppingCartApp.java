package ru.yandex.practicum.commerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableFeignClients
public class ShoppingCartApp {
    public static void main(String[] argc) {
        SpringApplication.run(ShoppingCartApp.class, argc);
    }
}
