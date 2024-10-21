package com.pragmatic.converter;

import com.pragmatic.dto.AccountDto;
import com.pragmatic.dto.UserDto;
import com.pragmatic.model.Account;
import com.pragmatic.model.User;
import org.springframework.stereotype.Component;


@Component
public  class DtoConverter {

    //ACCOUNT
    public AccountDto convertAccountToDto(Account account) {
        return  AccountDto.builder()
                .accountId(account.getId())
                .currencyId(account.getCurrencyId())
                .userId(account.getUserId())
                .balance(account.getBalance())
                .build();
    }

    public Account convertDtoToAccount(AccountDto accountDto) {
        return  Account.builder()
                .currencyId(accountDto.getCurrencyId())
                .userId(accountDto.getUserId())
                .balance(accountDto.getBalance())
                .build();
    }

//    USER
    public UserDto convertUserToDto(User user) {
        return UserDto.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }

    public User convertDtoToUser(UserDto userDto) {
        return User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .build();
    }


}
