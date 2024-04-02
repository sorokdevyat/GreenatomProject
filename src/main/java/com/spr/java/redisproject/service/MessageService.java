package com.spr.java.redisproject.service;

import com.spr.java.redisproject.dto.MessageDto;
import com.spr.java.redisproject.dto.paging.PageRequestDto;
import com.spr.java.redisproject.dto.paging.PageResponseDto;
import com.spr.java.redisproject.model.Message;
import com.spr.java.redisproject.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface MessageService {
    MessageDto create(MessageDto messageDto,String topicId);
    MessageDto editMessage(String newMessage,String messageId,String topicId);
    MessageDto deleteMessage(String id,String topicId);
}
