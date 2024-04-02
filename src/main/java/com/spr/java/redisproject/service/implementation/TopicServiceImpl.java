package com.spr.java.redisproject.service.implementation;

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
import com.spr.java.redisproject.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {
    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;
    private final MessageRepository messageRepository;
    private final AccountRepository accountRepository;


    @Override
    public PageResponseDto<Message> getAllMessagesFromTopic(PageRequestDto<String> pageRequestDto) {
        Topic topic = findById(pageRequestDto.getData());
        List<Message> topicMessages = topic.getTopicMessages();

        List<Message> list = topicMessages.stream().skip(pageRequestDto.getSize()*pageRequestDto.getPage()).limit(pageRequestDto.getSize()).toList();


        return PageResponseDto.<Message>builder()
                .page(pageRequestDto.getPage())
                .total(topicMessages.size())
                .size(list.size())
                .responsePage(list).build();
    }
    @Override
    public TopicDto createTopic(Topic topic, String firstMessage) {
        Message message = Message.builder()
                .message(firstMessage)
                .creationDate(OffsetDateTime.now())
                .deleted(false)
                .build();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("Account %s not fount", username)));
        message.setAccount(account);

        Message save1 = messageRepository.save(message);

        if (topic.getTopicMessages() == null){
            topic.setTopicMessages(new ArrayList<>());
        }
        topic.getTopicMessages().add(save1);
        topic.setAuthor(account);
        Topic save = topicRepository.save(topic);
        return topicMapper.fromTopicToDto(save);
    }
    @Override
    public PageResponseDto<TopicDto> findAll(PageRequestDto<Void> pageRequestDto) {
        Page<Topic> topics = topicRepository.findAll(PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize()));

        List<TopicDto> list = topics.getContent()
                .stream()
                .map(topicMapper::fromTopicToDto).toList();

        return PageResponseDto.<TopicDto>builder()
                .page(pageRequestDto.getPage())
                .total((int) topics.getTotalElements())
                .size(topics.getSize())
                .responsePage(list).build();
    }
    public Topic findById(String id) {
        Optional<Topic> topic = topicRepository.findById(id);
        if (topic.isEmpty()) throw new NoSuchElementException();
        return topic.get();
    }
}
