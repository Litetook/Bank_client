package com.pragmatic.repository;

import com.pragmatic.dto.AccountDto;
import com.pragmatic.model.Account;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

//@Repository
@Log4j2
@Getter @Setter
@Component
@NoArgsConstructor
public class AccountRepositoryImpl  {

    @Autowired
    private UserRepository userRepo;
    public AccountRepositoryImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

}
