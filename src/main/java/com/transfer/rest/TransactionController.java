package com.transfer.rest;

import com.transfer.dto.MoneyTransfer;

import javax.ws.rs.core.Response;

public interface TransactionController {

    Response submitMoneyTransfer(MoneyTransfer trx);
}
