package com.example.demo.kafka;

import com.example.demo.kafka.serde.CustomSerdes;
import com.example.demo.model.DummyEntity;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.Duration;

import static com.example.demo.kafka.Topic.INPUT_TOPIC;
import static com.example.demo.kafka.Topic.OUTPUT_TOPIC;


@Service
public class Stream {

    @Bean
    public KStream<String, DummyEntity> kStream(StreamsBuilder kStreamBuilder) {
        KStream<String, DummyEntity> stream = kStreamBuilder.stream(INPUT_TOPIC, Consumed.with(Serdes.String(), CustomSerdes.DummyEntity()));

        stream
                .groupByKey()
                .windowedBy(TimeWindows.ofSizeWithNoGrace(Duration.ofSeconds(60)))
                .reduce((aggValue, currValue) -> aggValue, Materialized.as("reduce-store"))
                .suppress(Suppressed.untilWindowCloses(Suppressed.BufferConfig.unbounded()).withName("suppress-store"))
                .toStream()
                .map((windowedId, value) -> new KeyValue<>(windowedId.key(), value))
                .to(OUTPUT_TOPIC, Produced.with(Serdes.String(), CustomSerdes.DummyEntity()));

        // stream.print(Printed.toSysOut());

        return stream;
    }
}
