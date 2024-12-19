package com.example.banking_system.Dto;
import lombok.*;

//@Data
//public class AccountDto {
//    private Long id;
//    private String holderName;
//    private double balance;
//}
public record AccountDto(Long id, String holderName, double balance) {
}