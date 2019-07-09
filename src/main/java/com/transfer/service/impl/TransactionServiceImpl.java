package com.transfer.service.impl;

import com.transfer.dto.AccountDto;
import com.transfer.dto.MoneyTransfer;
import com.transfer.entity.Account;
import com.transfer.exception.NoBalanceException;
import com.transfer.exception.NotAllowedOperationException;
import com.transfer.repository.AccountRepository;
import com.transfer.repository.impl.AccountRepositoryImpl;
import com.transfer.service.TransactionService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TransactionServiceImpl implements TransactionService {

    private static final Object monitorObject = new Object();
    private static final TransactionServiceImpl INSTANCE = new TransactionServiceImpl(AccountRepositoryImpl.getInstance());

    private final AccountRepository repository;

    private TransactionServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    public static TransactionServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<AccountDto> transfer(final MoneyTransfer trx) {
        Account source = repository.getById(trx.getSource());
        Account target = repository.getById(trx.getTarget());

        if (source == null || target == null) {
            throw new NotAllowedOperationException("Account(s) doesn't exist. | Source: " + source + ", Target: " + target);
        }

        return transferMoney(source, target, trx.getAmount());
    }

    private List<AccountDto> transferMoney(final Account sourceAccount,
                                           final Account targetAccount,
                                           final BigDecimal amount) {
        class TransferExecutor {
            private List<AccountDto> execute() {
                if (sourceAccount.getBalance().compareTo(amount) < 0) {
                    throw new NoBalanceException("Money Transfer can't be performed due to lack of funds on the account.");
                }

                sourceAccount.withdraw(amount);
                targetAccount.deposit(amount);

                return Collections.unmodifiableList(Arrays.asList(AccountDto.from(sourceAccount), AccountDto.from(targetAccount)));
            }
        }

        int sourceHash = System.identityHashCode(sourceAccount);
        int targetHash = System.identityHashCode(targetAccount);

        if (sourceHash < targetHash) {
            synchronized (sourceAccount) {
                synchronized (targetAccount) {
                    return new TransferExecutor().execute();
                }
            }
        } else if (sourceHash > targetHash) {
            synchronized (targetAccount) {
                synchronized (sourceAccount) {
                    return new TransferExecutor().execute();
                }
            }
        } else {
            synchronized (monitorObject) {
                synchronized (sourceAccount) {
                    synchronized (targetAccount) {
                        return new TransferExecutor().execute();
                    }
                }
            }
        }
    }
}