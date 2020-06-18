package com.study.studyrestfulservice.account;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
        Account account = accountDaoService.findOne(id);
        if (account == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
        return account;
    }

    @PostMapping("/users")
    public ResponseEntity<Account> createAccount(@Valid @RequestBody Account account){
        Account saveAccount = accountDaoService.save(account);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saveAccount.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteAccount(@PathVariable("id") Long id){
        Account account = accountDaoService.deleteById(id);
        if (account == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
    }

}
