package com.spr.java.redisproject.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.OffsetDateTime;
import java.util.UUID;

@RedisHash("Message")
@Data
@Builder
public class Message {
    @Id
    private String id;

    @Reference
    private Account account;

    @NotNull
    private String message;

    private OffsetDateTime creationDate;

    private boolean deleted;

}
