package com.spr.java.redisproject.dto.paging;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PageResponseDto<T> {
    private int page;
    private int total;
    private int size;
    private List<T> responsePage;
}
