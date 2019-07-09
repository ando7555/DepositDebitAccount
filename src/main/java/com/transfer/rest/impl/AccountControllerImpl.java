package com.transfer.rest.impl;

import com.transfer.entity.Account;
import com.transfer.repository.AccountRepository;
import com.transfer.repository.impl.AccountRepositoryImpl;
import com.transfer.rest.AccountController;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;


@Path("/accounts")
public class AccountControllerImpl implements AccountController {

    private final AccountRepository repository = AccountRepositoryImpl.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response getAllAccounts() {
        return Response.ok(Collections.unmodifiableCollection(repository.getAll())).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response getAccountById(@PathParam("id") String id) {
        Account account = repository.getById(id);
        if (account == null)
            return Response.noContent().build();

        return Response.ok(account).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response createAccount(Account account) {
        repository.addAccount(account);
        return Response.ok(account).build();
    }


}