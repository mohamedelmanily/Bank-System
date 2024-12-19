package com.example.banking_system.repositry;
import com.example.banking_system.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepositry extends JpaRepository<Account,Long> {
}
