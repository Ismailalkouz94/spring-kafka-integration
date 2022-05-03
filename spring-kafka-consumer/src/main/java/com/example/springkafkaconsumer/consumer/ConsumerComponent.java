package com.example.springkafkaconsumer.consumer;

import com.example.springkafkaconsumer.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConsumerComponent {

    @KafkaListener(topics = "${spring.kafka.topicName}")
    public void listen(Person event) {
        log.info("Received Message: " + event);
    }

}
