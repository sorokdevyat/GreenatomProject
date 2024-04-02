package com.spr.java.redisproject.repos;

import com.spr.java.redisproject.dto.AccountDto;
import com.spr.java.redisproject.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account,String> {
    List<Account> findAll();
    void deleteAccountByUsername(String username);
    Optional<Account> findByUsername(String username);
}
