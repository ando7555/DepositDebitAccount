package com.transfer.service;

import com.transfer.dto.AccountDto;
import com.transfer.dto.MoneyTransfer;

import java.util.List;

public interface TransactionService {

    List<AccountDto> transfer(final MoneyTransfer trx);
}
