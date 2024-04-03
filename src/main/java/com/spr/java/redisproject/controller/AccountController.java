package com.spr.java.redisproject.controller;

import com.spr.java.redisproject.dto.AccountDto;
import com.spr.java.redisproject.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/greenproject/account")
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/getAll")
    public List<AccountDto> getAllAccount(){
        return accountService.findAll();
    }
    @PostMapping("/create")
    public AccountDto createAccount(@RequestBody AccountDto accountDto){
        AccountDto save = accountService.save(accountDto);
        return save;
    }

}
