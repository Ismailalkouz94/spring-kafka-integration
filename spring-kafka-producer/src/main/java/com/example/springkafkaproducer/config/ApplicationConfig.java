package com.example.springkafkaproducer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;


@Data
@Configuration
@ConfigurationProperties(prefix = "spring.kafka")
public class ApplicationConfig {

    private String bootstrapServers;
    private String topicName;

    @PostConstruct
    public void init(){
        System.out.println("bootstrapServers: " + bootstrapServers);
    }

}
