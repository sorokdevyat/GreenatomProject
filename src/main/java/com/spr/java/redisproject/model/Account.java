package com.spr.java.redisproject.model;

import com.spr.java.redisproject.common.enums.AccountRole;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Data
@RedisHash("Account")
@Builder
public class Account {
    @Id
    private String id;

    @NotNull
    @Size(min = 2,max = 32)
    @Indexed
    private String username;

    @NotNull
    @Indexed
    private String password;

    @NotNull
    private AccountRole accountRole;
}
