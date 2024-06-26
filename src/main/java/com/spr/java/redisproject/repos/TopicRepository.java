package com.spr.java.redisproject.repos;


import com.spr.java.redisproject.model.Topic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends CrudRepository<Topic,String>,PagingAndSortingRepository<Topic,String>{
}
