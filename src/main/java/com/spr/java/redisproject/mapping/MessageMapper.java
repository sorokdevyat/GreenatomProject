package com.spr.java.redisproject.mapping;

import com.spr.java.redisproject.dto.MessageDto;
import com.spr.java.redisproject.model.Account;
import com.spr.java.redisproject.model.Message;
import com.spr.java.redisproject.model.Topic;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class MessageMapper {
    public Message fromDtoToMessage(MessageDto messageDto){
        Message message = Message.builder()
                .id(messageDto.getId())
                .message(messageDto.getMessage())
                .creationDate(messageDto.getCreationDate())
                .deleted(messageDto.isDeleted())
                .build();
        if (!ObjectUtils.isEmpty(messageDto.getAccountId())){
            message.setAccount(Account.builder().id(messageDto.getAccountId()).build());
        }
        return message;
    }
    public MessageDto fromMessageToDto(Message message){
        MessageDto messageDto = MessageDto.builder()
                .id(message.getId())
                .message(message.getMessage())
                .creationDate(message.getCreationDate())
                .deleted(message.isDeleted())
                .build();
        if (!ObjectUtils.isEmpty(message.getAccount())){
            messageDto.setAccountId(message.getAccount().getId());
        }

        return messageDto;
    }
}
