package com.spr.java.redisproject.model;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.List;

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
