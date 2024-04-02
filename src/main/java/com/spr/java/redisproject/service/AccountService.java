package com.spr.java.redisproject.service;

import com.spr.java.redisproject.dto.AccountDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AccountService extends UserDetailsService {
    AccountDto save(AccountDto accountDto);
    List<AccountDto> findAll();
    void deleteByUsername(String username);
}
