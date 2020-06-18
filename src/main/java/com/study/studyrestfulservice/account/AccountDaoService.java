package com.study.studyrestfulservice.account;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class AccountDaoService {
    private static List<Account> accounts = new ArrayList<>();

    private static Long accountCount = 3L;

    static {
        accounts.add(new Account(1L, "account1", LocalDateTime.now()));
        accounts.add(new Account(2L, "account2", LocalDateTime.now()));
        accounts.add(new Account(3L, "account3", LocalDateTime.now()));
    }

    public List<Account> findAll(){
        return accounts;
    }

    public Account save(Account account){
        if (account.getId() == null){
            account.setId(++accountCount);
        }
        accounts.add(account);
        return account;
    }

    public Account findOne(Long id){
        for (Account account : accounts){
            if (account.getId() == id){
                return account;
            }
        }
        return null;
    }

    public Account deleteById(Long id){
        Iterator<Account> iterator = accounts.iterator();

        while(iterator.hasNext()){
            Account account = iterator.next();

            if (account.getId() == id){
                iterator.remove();
                return account;
            }
        }
        return null;
    }
}
