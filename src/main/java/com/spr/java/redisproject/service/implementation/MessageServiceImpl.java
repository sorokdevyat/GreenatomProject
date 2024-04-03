package com.spr.java.redisproject.service.implementation;

import com.spr.java.redisproject.common.enums.AccountRole;
import com.spr.java.redisproject.common.exceptions.MessageNotAttachedToUserException;
import com.spr.java.redisproject.dto.MessageDto;
import com.spr.java.redisproject.dto.TopicDto;
import com.spr.java.redisproject.dto.paging.PageRequestDto;
import com.spr.java.redisproject.dto.paging.PageResponseDto;
import com.spr.java.redisproject.mapping.MessageMapper;
import com.spr.java.redisproject.mapping.TopicMapper;
import com.spr.java.redisproject.model.Account;
import com.spr.java.redisproject.model.Message;
import com.spr.java.redisproject.model.Topic;
import com.spr.java.redisproject.repos.AccountRepository;
import com.spr.java.redisproject.repos.MessageRepository;
import com.spr.java.redisproject.repos.TopicRepository;
import com.spr.java.redisproject.service.MessageService;
import com.spr.java.redisproject.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.security.Security;
import java.time.OffsetDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;
    private final TopicService topicService;
    private final AccountRepository accountRepository;
    private final TopicRepository topicRepository;

    @Override
    public MessageDto create(MessageDto messageDto,String topicId) {
        Message toMessage = messageMapper.fromDtoToMessage(messageDto);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Account %s not fount", username)));
        toMessage.setAccount(account);
        toMessage.setCreationDate(OffsetDateTime.now());
        toMessage.setDeleted(false);
        Message message = messageRepository.save(toMessage);

        Topic topic = topicService.findById(topicId);

        if (topic.getTopicMessages() == null){
            topic.setTopicMessages(new ArrayList<>());
        }
        topic.getTopicMessages().add(message);

        topicRepository.save(topic);
        return messageMapper.fromMessageToDto(message);
    }

    @Override
    public MessageDto editMessage(String newMessage, String messageId,String topicId) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new NoSuchElementException(String.format("Message with id %s not found", messageId)));

        allowAccess(messageId);
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new NoSuchElementException(String.format("Topuc with id %s not found", topicId)));

        topic.getTopicMessages().removeIf(m -> m.getId().equals(messageId));

        if (topic.getTopicMessages() == null){
            topic.setTopicMessages(new ArrayList<>());
        }

        message.setMessage(newMessage);

        Message save1 = messageRepository.save(message);
        topic.getTopicMessages().add(save1);
        topicRepository.save(topic);
        return messageMapper.fromMessageToDto(message);
    }
    public void allowAccess(String messageId){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Account %s not found", username)));

        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new NoSuchElementException(String.format("Message with id %s not found", messageId)));


        if (!Objects.equals(message.getAccount().getId(),account.getId())){
            if (!AccountRole.ADMIN.equals(account.getAccountRole())){
                throw new MessageNotAttachedToUserException(String.format("Message was created by account with id = %s",message.getAccount().getId()));

            }
        }

    }

    @Override
    public MessageDto deleteMessage(String messageId,String topicId) {
        Message message = findById(messageId);
        allowAccess(messageId);
        Topic topic = topicRepository.findById(topicId).orElseThrow(NoSuchElementException::new);
        message.setDeleted(true);

        topic.getTopicMessages().removeIf(m -> m.getId().equals(messageId));

        topicRepository.save(topic);
        return messageMapper.fromMessageToDto(message);
    }


    public Message findById(String id){
        Optional<Message> byId = messageRepository.findById(id);
        if (byId.isEmpty()){
            throw new NoSuchElementException();
        }
        return byId.get();
    }
}
