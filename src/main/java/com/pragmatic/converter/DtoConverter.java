package com.pragmatic.converter;

import com.pragmatic.dto.impl.AccountDtoImpl;
import com.pragmatic.dto.impl.UserDtoImpl;
import com.pragmatic.model.Account;
import com.pragmatic.model.User;

public  class DtoConverter {

    //ACCOUNT
    public AccountDtoImpl convertAccountToDto(Account account) {
        return  AccountDtoImpl.builder()
                .accountId(account.getId())
                .currencyId(account.getCurrencyId())
                .userId(account.getUserId())
                .balance(account.getBalance())
                .build();
    }

    public Account convertDtoToAccount(AccountDtoImpl accountDtoImpl) {
        return  Account.builder()
                .currencyId(accountDtoImpl.getCurrencyId())
                .userId(accountDtoImpl.getUserId())
                .balance(accountDtoImpl.getBalance())
                .build();
    }

//    USER
    public UserDtoImpl convertUserToDto(User user) {
        return UserDtoImpl.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .password(user.getPassword())
                .build();
    }

    public User convertDtoToUser(UserDtoImpl userDto) {
        return User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }


}
