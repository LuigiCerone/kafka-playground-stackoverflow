package com.example.demo.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class Topic {
    public final static String INPUT_TOPIC = "input_topic";
    public final static String OUTPUT_TOPIC = "output_topic";

    @Bean
    public NewTopic inputTopic() {
        return TopicBuilder.name(INPUT_TOPIC)
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic outputTopic() {
        return TopicBuilder.name(OUTPUT_TOPIC)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
