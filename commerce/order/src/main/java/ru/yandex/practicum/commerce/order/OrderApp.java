package ru.yandex.practicum.commerce.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import ru.yandex.practicum.common.ErrorDecoderConfig;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(defaultConfiguration = ErrorDecoderConfig.class)
public class OrderApp {
    public static void main(String[] args) {
        SpringApplication.run(OrderApp.class, args);
    }
}
