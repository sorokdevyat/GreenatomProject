package com.spr.java.redisproject.controller;

import com.spr.java.redisproject.dto.TopicDto;
import com.spr.java.redisproject.dto.paging.PageRequestDto;
import com.spr.java.redisproject.dto.paging.PageResponseDto;
import com.spr.java.redisproject.mapping.TopicMapper;
import com.spr.java.redisproject.model.Message;
import com.spr.java.redisproject.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/greenproject/topic")
public class TopicController {
    private final TopicService topicService;
    private final TopicMapper topicMapper;

    @PostMapping("/getAll")
    public PageResponseDto<TopicDto> getAll(@RequestBody PageRequestDto<Void> pageRequestDto){
        return topicService.findAll(pageRequestDto);
    }
    @PostMapping("/create")
    public TopicDto create(@RequestBody TopicDto topicDto,@RequestParam String firstMessage){
        return topicService.createTopic(topicMapper.fromDtoToTopic(topicDto), firstMessage);
    }
    @PostMapping("/getMessageByTopicID")
    public PageResponseDto<Message> getMessageByTopicID(@RequestBody PageRequestDto<String> pageRequestDto){
        return topicService.getAllMessagesFromTopic(pageRequestDto);
    }
}
