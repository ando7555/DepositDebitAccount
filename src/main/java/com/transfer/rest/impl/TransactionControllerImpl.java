package com.transfer.rest.impl;

import com.transfer.dto.AccountDto;
import com.transfer.dto.MoneyTransfer;
import com.transfer.rest.TransactionController;
import com.transfer.service.TransactionService;
import com.transfer.service.impl.TransactionServiceImpl;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/transactions")
public class TransactionControllerImpl implements TransactionController {

    private final TransactionService transactionService = TransactionServiceImpl.getInstance();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response submitMoneyTransfer(MoneyTransfer trx) {
        List<AccountDto> result = transactionService.transfer(trx);
        return Response.ok().entity(result).build();
    }

}