package com.spr.java.redisproject.mapping;


import com.spr.java.redisproject.dto.TopicDto;
import com.spr.java.redisproject.model.Account;
import com.spr.java.redisproject.model.Message;
import com.spr.java.redisproject.model.Topic;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;

@Component
public class TopicMapper {
    public Topic fromDtoToTopic(TopicDto topicDto){
        Topic topic = Topic.builder()
                .id(topicDto.getId())
                .title(topicDto.getTitle())
                .build();
        if (!ObjectUtils.isEmpty(topicDto.getAuthor_id())){
            topic.setAuthor(Account.builder().id(topicDto.getAuthor_id()).build());
        }
        if (!CollectionUtils.isEmpty(topicDto.getMessages_id())){
            for (String id : topicDto.getMessages_id()){
                topic.getTopicMessages().add(Message.builder().id(id).build());
            }
        }
        return topic;
    }

    public TopicDto fromTopicToDto(Topic topic){
        TopicDto topicDto = TopicDto.builder()
                .id(topic.getId())
                .title(topic.getTitle())
                .build();
        if (!ObjectUtils.isEmpty(topic.getAuthor())){
            topicDto.setAuthor_id(topic.getAuthor().getId());
        }
        if (!CollectionUtils.isEmpty(topic.getTopicMessages())){
            for (Message message : topic.getTopicMessages()){
                if (topicDto.getMessages_id() == null){
                    topicDto.setMessages_id(new ArrayList<>());
                }
                topicDto.getMessages_id().add(message.getId());
            }
        }
        return topicDto;
    }
}
