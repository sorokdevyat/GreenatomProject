package com.spr.java.redisproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spr.java.redisproject.model.Message;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopicDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    private String title;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<String> messages_id = new ArrayList<>();
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String author_id;
}
