package com.transfer.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AccountExistenceException extends RuntimeException implements ExceptionMapper<AccountExistenceException> {

    public AccountExistenceException(String accountId) {
        super("Account with ID:" + accountId + " already exists. Duplicates are not allowed.");
    }

    public AccountExistenceException() {
        super();
    }

    @Override
    public Response toResponse(AccountExistenceException exception) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(exception.getMessage())
                .type("text/plain")
                .build();
    }
}