package com.transfer.repository.impl;


import com.transfer.entity.Account;
import com.transfer.exception.AccountExistenceException;
import com.transfer.repository.AccountRepository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AccountRepositoryImpl implements AccountRepository {

    private static final AccountRepositoryImpl INSTANCE = new AccountRepositoryImpl(new ConcurrentHashMap<>());
    private final Map<String, Account> accounts;

    private AccountRepositoryImpl(Map<String, Account> accounts) {
        this.accounts = accounts;
    }

    public static AccountRepositoryImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Account getById(String id) {
        return accounts.get(id);
    }

    @Override
    public Collection<Account> getAll() {
        return accounts.values();
    }

    @Override
    public Account addAccount(Account account) {
        Account accountExists = accounts.putIfAbsent(account.getId(), account);
        if (accountExists != null) {
            throw new AccountExistenceException(accountExists.getId());
        }

        return getById(account.getId());
    }

    @Override
    public void removeAll() {
        synchronized (accounts) {
            accounts.clear();
        }
    }
}