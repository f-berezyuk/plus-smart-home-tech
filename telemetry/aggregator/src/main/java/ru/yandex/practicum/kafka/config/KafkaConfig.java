package ru.yandex.practicum.kafka.config;

import java.util.Map;
import java.util.Properties;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "aggregator.kafka")
public class KafkaConfig {
    private Map<String, String> topics;
    private Properties producer;
    private Properties consumer;

    @PostConstruct
    public void init() {
        log.info("Kafka topics: {}", topics);
        log.info("Kafka producer config: {}", producer);
        log.info("Kafka consumer config: {}", consumer);
    }
}