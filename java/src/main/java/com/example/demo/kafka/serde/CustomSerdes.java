package com.example.demo.kafka.serde;

import com.example.demo.model.DummyEntity;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

public class CustomSerdes {
    public static Serde<DummyEntity> DummyEntity() {
        return Serdes.serdeFrom(new JsonSerializer<DummyEntity>(), new JsonDeserializer<>(DummyEntity.class));
    }
}
