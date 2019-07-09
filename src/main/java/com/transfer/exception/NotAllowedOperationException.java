package com.transfer.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotAllowedOperationException extends RuntimeException implements ExceptionMapper<NotAllowedOperationException> {

    public NotAllowedOperationException() {
        super("Operation can't be performed.");
    }

    public NotAllowedOperationException(String message) {
        super(message);
    }

    @Override
    public Response toResponse(NotAllowedOperationException exception) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(exception.getMessage())
                .type("text/plain")
                .build();
    }
}