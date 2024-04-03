package com.spr.java.redisproject.repos;

import com.spr.java.redisproject.dto.paging.PageRequestDto;
import com.spr.java.redisproject.model.Message;

import com.spr.java.redisproject.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message,String> {
}
