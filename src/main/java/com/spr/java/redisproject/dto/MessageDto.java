package com.spr.java.redisproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.OffsetDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String accountId;
    private String message;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime creationDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean isDeleted;
}
