package com.study.studyrestfulservice.account;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jpa")
@RequiredArgsConstructor
public class AccountJPAController {

    private final AccountRepository accountRepository;

    @GetMapping("/accounts")
    public List<Account> retrieveAllAccounts(){
        return accountRepository.findAll();
    }

}
