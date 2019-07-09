package com.transfer.dto;

import com.transfer.entity.Account;

public class AccountDto {

    private final String id;
    private final String balance;

    private AccountDto(String id, String balance) {
        this.id = id;
        this.balance = balance;
    }

    public static AccountDto from(Account account) {
        return new AccountDto(account.getId(), String.valueOf(account.getBalance()));
    }

    public String getId() {
        return id;
    }

    public String getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "AccountDto{" +
                "id='" + id + '\'' +
                ", balance='" + balance + '\'' +
                '}';
    }
}