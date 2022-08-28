package com.example.demo.kafka;

import com.example.demo.model.DummyEntity;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

import static com.example.demo.kafka.Topic.INPUT_TOPIC;

@Service
public class Consumer {
    private static final Logger LOGGER = Logger.getLogger(Consumer.class.getName());

    @KafkaListener(topics = INPUT_TOPIC)
    public void consume(ConsumerRecord<String, DummyEntity> record) {
        LOGGER.info(String.format("Consumed message -> %s", record.value()));
    }
}
