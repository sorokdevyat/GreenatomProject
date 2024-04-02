package com.spr.java.redisproject.service;

import com.spr.java.redisproject.dto.MessageDto;
import com.spr.java.redisproject.dto.TopicDto;
import com.spr.java.redisproject.dto.paging.PageRequestDto;
import com.spr.java.redisproject.dto.paging.PageResponseDto;
import com.spr.java.redisproject.model.Message;
import com.spr.java.redisproject.model.Topic;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TopicService {
    TopicDto createTopic(Topic topic, String firstMessage);

    PageResponseDto<TopicDto> findAll(PageRequestDto<Void> pageRequestDto);

    Topic findById(String id);


    PageResponseDto<Message> getAllMessagesFromTopic(PageRequestDto<String> pageRequestDto);
}
