package com.study.studyrestfulservice.account;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountDaoService accountDaoService;

    @GetMapping("/users")
    public List<Account> retrieveAllUsers(){
        return accountDaoService.findAll();
    }

    // GET / users/1
    @GetMapping("/users/{id}")
    public Account retrieveUser(@PathVariable("id") Long id){
        return accountDaoService.findOne(id);
    }

    @PostMapping("/users")
    public void createAccount(@RequestBody Account account){
        Account saveAccount = accountDaoService.save(account);
    }


}
