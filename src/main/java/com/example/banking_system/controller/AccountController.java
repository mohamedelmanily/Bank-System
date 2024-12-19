package com.example.banking_system.controller;
import com.example.banking_system.Dto.AccountDto;
import com.example.banking_system.exception.AccountExceptions;
import com.example.banking_system.repositry.AccountRepositry;
import com.example.banking_system.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/accounts")
public class AccountController {

    private final AccountServiceImpl accountServiceImpl;
    private final AccountRepositry accountRepositry;

    @Autowired
    public AccountController(AccountServiceImpl accountServiceImpl, AccountRepositry accountRepositry) {
        this.accountServiceImpl = accountServiceImpl;
        this.accountRepositry = accountRepositry;
    }

    @GetMapping(value = "/allAcounts")
    public List<AccountDto> getAccounts() {
        return accountServiceImpl.getAllAccounts();
    }

    @PostMapping(value = "/addAccount")
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountServiceImpl.createAccount(accountDto),HttpStatus.CREATED);
    }

    @GetMapping(value = "/account/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable long id) {
        AccountDto account = accountServiceImpl.getAccountById(id);
        return ResponseEntity.ok(account);
    }

    @PutMapping(value = "/{id}/deposit")
    public AccountDto deposit(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        return accountServiceImpl.deposit(id, amount);
    }

    @PutMapping(value = "/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        return ResponseEntity.ok(accountServiceImpl.withdraw(id, amount));
    }

    @DeleteMapping(value = "/{id}/deleteAccount")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        accountRepositry.findById(id).orElseThrow(() -> new AccountExceptions("Account not found"));
        return new ResponseEntity<>(accountServiceImpl.deleteAccount(id),HttpStatus.OK);
    }
}
