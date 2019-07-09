package com.transfer.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NoBalanceException extends RuntimeException implements ExceptionMapper<NoBalanceException> {

    public NoBalanceException() {
        super("Insufficient account balance to perform this operation.");
    }

    public NoBalanceException(String message) {
        super(message);
    }

    @Override
    public Response toResponse(NoBalanceException exception) {
        return Response
                .status(Response.Status.CONFLICT)
                .entity(exception.getMessage())
                .type("text/plain")
                .build();
    }
}