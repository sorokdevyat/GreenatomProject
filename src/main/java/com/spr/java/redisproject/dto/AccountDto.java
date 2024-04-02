package com.spr.java.redisproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spr.java.redisproject.common.enums.AccountRole;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;
    private String username;
    private String password;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private AccountRole accountRole;
}
