package com.example.banking_system.service;

import com.example.banking_system.Dto.AccountDto;
import com.example.banking_system.entity.Account;
import com.example.banking_system.exception.AccountExceptions;
import com.example.banking_system.mapper.AccountMapper;
import com.example.banking_system.repositry.AccountRepositry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    //Constructor
    private final AccountRepositry accountRepositry;
    public AccountServiceImpl(AccountRepositry accountRepositry) {
        this.accountRepositry = accountRepositry;
    }

    //Create account
    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.toAccount(accountDto);
        accountRepositry.save(account);
        return AccountMapper.toAccountDto(account);
    }

    //Get All Accounts
    @Override
    public List<AccountDto> getAllAccounts() {
//        List<Account> accounts = accountRepositry.findAll();
//        List<AccountDto> accountDtos = new ArrayList<>();
//        for (Account account : accounts) {
//            accountDtos.add(AccountMapper.toAccountDto(account));
//        }
//        return accountDtos;
        return accountRepositry.findAll().stream().map(AccountMapper::toAccountDto).collect(Collectors.toList());
    }

    //Get account
    @Override
    public AccountDto getAccountById(long id) {
        return AccountMapper.toAccountDto(accountRepositry.findById(id).orElseThrow(() -> new AccountExceptions("Account not found")));
    }

    //Deposit
    @Override
    public AccountDto deposit(long id, double amount) {
        Account account = accountRepositry.findById(id).orElseThrow(() -> new AccountExceptions("Account not found"));
        account.setBalance(account.getBalance() + amount);
        accountRepositry.save(account);
        return AccountMapper.toAccountDto(account);
    }


    //Withdraw
    @Override
    public AccountDto withdraw(long id, double amount) {
        Account account=accountRepositry.findById(id).orElseThrow(() -> new AccountExceptions("Account not found"));
        if(account.getBalance()>amount){
        account.setBalance(account.getBalance() - amount);
        accountRepositry.save(account);
        }
        else throw new AccountExceptions("there is no enough money to withdraw");
        return AccountMapper.toAccountDto(account);
    }
    //Delete account
    @Override
    public String deleteAccount(long id) {
        accountRepositry.deleteById(id);
        return "account with id: " + id+ " deleted successfully";
    }



}
