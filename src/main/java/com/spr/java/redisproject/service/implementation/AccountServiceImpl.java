package com.spr.java.redisproject.service.implementation;

import com.spr.java.redisproject.common.enums.AccountRole;
import com.spr.java.redisproject.common.exceptions.NotUniqueUsernameException;
import com.spr.java.redisproject.dto.AccountDto;
import com.spr.java.redisproject.mapping.AccountMapper;
import com.spr.java.redisproject.model.Account;
import com.spr.java.redisproject.repos.AccountRepository;
import com.spr.java.redisproject.service.AccountService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AccountDto save(AccountDto accountDto) {
        accountDto.setAccountRole(AccountRole.READER);
        if (!ObjectUtils.isEmpty(accountRepository.findByUsername(accountDto.getUsername()))){
            throw new NotUniqueUsernameException(String.format("Account with username : %s already exist",accountDto.getUsername()));
        }
        accountDto.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        Account save = accountRepository.save(accountMapper.fromDtoToAccount(accountDto));
        return accountMapper.fromAccountToDto(save);
    }
    @Override
    public List<AccountDto> findAll() {
        return accountRepository.findAll()
                .stream()
                .map(accountMapper::fromAccountToDto).toList();
    }

    @Override
    public void deleteByUsername(String username) {
        accountRepository.deleteAccountByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException(String.format("Account %s not fount",username)));
        return new User(username,account.getPassword(), Collections.emptyList());
    }
    @PostConstruct
    public void initAdmin() {
        accountRepository.findByUsername("root")
                .orElseGet(() -> accountRepository.save(Account.builder()
                        .username("root")
                        .password(passwordEncoder.encode("root"))
                        .accountRole(AccountRole.ADMIN)
                        .build()));
    }
}
