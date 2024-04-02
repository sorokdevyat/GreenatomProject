package com.spr.java.redisproject.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RedisHash("Topic")
@Builder
@Data
public class Topic {
    @Id
    @Indexed
    private String id;

    @NotNull
    @Indexed
    private String title;

    @Reference
    private Account author;

    private List<Message> topicMessages;
}
