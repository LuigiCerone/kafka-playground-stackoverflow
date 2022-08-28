package com.example.demo.kafka;

import com.example.demo.model.DummyEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import static com.example.demo.kafka.Topic.INPUT_TOPIC;

@Service
public class Producer {
    private final KafkaTemplate<String, DummyEntity> kafkaTemplate;

    public Producer(KafkaTemplate<String, DummyEntity> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedDelayString = "10000")
    public void publishDummyMessageTopic() {
        int id = generateId(5);

        kafkaTemplate.send(INPUT_TOPIC, String.valueOf(id), getUser(id));
    }

    private DummyEntity getUser(int id) {
        return new DummyEntity(id, "name" + id);
    }

    private int generateId(int max) {
        return getRandomNumber(max);
    }

    private int getRandomNumber(int max) {
        return (int) ((Math.random() * (max)) + 0);
    }
}
