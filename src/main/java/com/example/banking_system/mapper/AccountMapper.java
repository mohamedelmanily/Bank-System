package com.example.banking_system.mapper;

import com.example.banking_system.Dto.AccountDto;
import com.example.banking_system.entity.Account;

public class AccountMapper {
    public static Account toAccount(AccountDto accountDto) {
        Account account = new Account();
//        account.setId(accountDto.id());
        account.setHolderName(accountDto.holderName());
        account.setBalance(accountDto.balance());
        return account;
    }

    public static AccountDto toAccountDto(Account account) {

        return new AccountDto(account.getId(),
                               account.getHolderName(),
                               account.getBalance());


    }
}
