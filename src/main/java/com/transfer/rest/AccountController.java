package com.transfer.rest;

import com.transfer.entity.Account;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

public interface AccountController {

    Response getAllAccounts();

    Response getAccountById(@PathParam("id") String id);

    Response createAccount(Account account);
}
