package com.spr.java.redisproject.controller;

import com.spr.java.redisproject.dto.MessageDto;
import com.spr.java.redisproject.dto.paging.PageRequestDto;
import com.spr.java.redisproject.dto.paging.PageResponseDto;
import com.spr.java.redisproject.mapping.MessageMapper;
import com.spr.java.redisproject.model.Message;
import com.spr.java.redisproject.model.Topic;
import com.spr.java.redisproject.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/greenproject/messages")
public class MessageController {
    private final MessageService messageService;

    @PostMapping("/create")
    public MessageDto create(@RequestBody MessageDto messageDto,@RequestParam String topicId){
        return messageService.create(messageDto,topicId);
    }
    @PostMapping("/edit")
    public MessageDto edit(@RequestParam String newMessage,@RequestParam String messageId,@RequestParam String topicId){
        return messageService.editMessage(newMessage,messageId,topicId);
    }
    @PostMapping("/delete")
    public MessageDto delete(@RequestParam String messageId,@RequestParam String topicId){
        return messageService.deleteMessage(messageId,topicId);
    }
}
