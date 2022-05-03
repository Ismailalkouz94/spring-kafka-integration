package com.example.springkafkaproducer.producer;

import com.example.springkafkaproducer.config.ApplicationConfig;
import com.example.springkafkacommon.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Component
public class ProducerComponent {

    @Autowired
    private ApplicationConfig applicationConfig;

    @Autowired
    private KafkaTemplate<String, Person> kafkaTemplate;

    public void sendMessage(Person msg) {
        ListenableFuture<SendResult<String, Person>> future = kafkaTemplate.send(applicationConfig.getTopicName(),msg);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Person>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("Unable to send message:" + ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Person> result) {
                log.info("Sent message:" + result.getProducerRecord().value() +
                        ",  offset:" + result.getRecordMetadata().offset() );
            }
        });
    }

    @Scheduled(fixedDelay = 5000)
    public void test(){
        sendMessage(new Person(1L,"ismail","ismail@gmail.com"));
        log.info("Test Message sent");
    }

}
