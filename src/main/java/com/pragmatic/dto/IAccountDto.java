package com.pragmatic.dto;

import java.util.List;

public interface IAccountDto {
    AccountDto getAccountById(Integer id);
    List<AccountDto> getAccounts();
}
