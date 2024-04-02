package com.spr.java.redisproject.dto.paging;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageRequestDto<T> {
    private int page;
    private int size;
    private T data;
}
