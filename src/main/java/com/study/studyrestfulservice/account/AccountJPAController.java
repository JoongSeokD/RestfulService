package com.study.studyrestfulservice.account;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.LinkBuilder;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/jpa")
@RequiredArgsConstructor
public class AccountJPAController {

    private final AccountRepository accountRepository;

    private final PostRepository postRepository;

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

    @DeleteMapping("/accounts/{id}")
    public void deleteAccount(@PathVariable Long id){
        accountRepository.deleteById(id);
    }

    @PostMapping("/accounts")
    public ResponseEntity<Account> createAccount(@Valid @RequestBody Account account){
        Account savedAccount = accountRepository.save(account);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(savedAccount.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/accounts/{id}/posts")
    public List<Post> retrieveAllPostsByUser(@PathVariable("id") Long id){
        Optional<Account> account = accountRepository.findById(id);
        if (account.isEmpty()){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
        return account.get().getPosts();
    }


    @PostMapping("/accounts/{id}/posts")
    public ResponseEntity<Post> createPost(@PathVariable("id") Long id, @RequestBody Post post){
        Account account = accountRepository.findById(id).get();
        post.setAccount(account);
        account.getPosts().add(post);
        Post savedPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

}
