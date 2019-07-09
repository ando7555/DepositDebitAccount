package com.transfer.repository;

import com.transfer.entity.Account;

import java.util.Collection;

public interface AccountRepository {


    Account getById(String id);


    Collection<Account> getAll();

    Account addAccount(Account account);

    void removeAll() ;
}
