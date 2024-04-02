package com.spr.java.redisproject.mapping;

import com.spr.java.redisproject.dto.AccountDto;
import com.spr.java.redisproject.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public Account fromDtoToAccount(AccountDto accountDto){
        Account account = Account.builder()
                .id(accountDto.getId())
                .username(accountDto.getUsername())
                .password(accountDto.getPassword())
                .accountRole(accountDto.getAccountRole())
                .build();
        return account;
    }
    public AccountDto fromAccountToDto(Account account){
        AccountDto accountDto = AccountDto.builder()
                .id(account.getId())
                .username(account.getUsername())
                .password(account.getPassword())
                .accountRole(account.getAccountRole())
                .build();
        return accountDto;
    }
}
