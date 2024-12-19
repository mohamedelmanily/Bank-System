package com.example.banking_system.service;

import com.example.banking_system.Dto.AccountDto;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);

    List<AccountDto> getAllAccounts();

    AccountDto getAccountById(long id);

    AccountDto deposit(long id, double amount);

    AccountDto withdraw(long id, double amount);

    String deleteAccount(long id);
}
