package com.study.studyrestfulservice.account;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.LinkBuilder;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/jpa")
@RequiredArgsConstructor
public class AccountJPAController {

    private final AccountRepository accountRepository;

    @GetMapping("/accounts")
    public List<Account> retrieveAllAccounts(){
        return accountRepository.findAll();
    }

    @GetMapping("/accounts/{id}")
    public Resource<Account> retrieveAccount(@PathVariable("id") Long id){
        Optional<Account> account = accountRepository.findById(id);
        if (account.isEmpty()){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        Resource<Account> resource = new Resource<>(account.get());
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllAccounts());
        resource.add(linkTo.withRel("all-users"));

        return resource;

    }

}
