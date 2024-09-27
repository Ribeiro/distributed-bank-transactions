package br.tec.gtech.distributed_bank_transactions.distributed.service;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.tec.gtech.distributed_bank_transactions.distributed.entity.TransactionControl;
import br.tec.gtech.distributed_bank_transactions.distributed.enums.TransactionStatus;
import br.tec.gtech.distributed_bank_transactions.distributed.repository.TransactionControlRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DistributedTransactionService {

    private static final String TRANSACTION_NOT_FOUND = "Transaction not found";
    private static final String TRANSACTION_WAS_MODIFIED_BY_ANOTHER_THREAD_PLEASE_TRY_AGAIN = "Transaction was modified by another thread. Please, try again.";
    private static final String TRANSACTION_ALREADY_COMPLETED = "Transaction already completed";
    private final TransactionControlRepository transactionControlRepository;

    @Transactional
    public void beginTransaction(String transactionKey) {
        try {
            Optional<TransactionControl> existingTransaction = transactionControlRepository
                    .findByTransactionKey(transactionKey);
            if (existingTransaction.isPresent()) {
                TransactionControl transaction = existingTransaction.get();
                if (transaction.getStatus() == TransactionStatus.COMPLETED) {
                    throw new IllegalStateException(TRANSACTION_ALREADY_COMPLETED);
                }

            } else {
                TransactionControl newTransaction = new TransactionControl();
                newTransaction.setTransactionKey(transactionKey);
                newTransaction.setStatus(TransactionStatus.IN_PROGRESS);
                newTransaction.setLastUpdated(LocalDateTime.now());
                transactionControlRepository.save(newTransaction);
            }
        } catch (OptimisticLockingFailureException e) {
            throw new IllegalStateException(
                    TRANSACTION_WAS_MODIFIED_BY_ANOTHER_THREAD_PLEASE_TRY_AGAIN, e);
        }
    }

    @Transactional
    public void completeTransaction(String transactionKey) {
        try {
            TransactionControl transaction = transactionControlRepository.findByTransactionKey(transactionKey)
                    .orElseThrow(() -> new IllegalStateException(TRANSACTION_NOT_FOUND));

            transaction.setStatus(TransactionStatus.COMPLETED);
            transaction.setLastUpdated(LocalDateTime.now());
            transactionControlRepository.save(transaction);
        } catch (OptimisticLockingFailureException e) {
            throw new IllegalStateException(
                    TRANSACTION_WAS_MODIFIED_BY_ANOTHER_THREAD_PLEASE_TRY_AGAIN, e);
        }
    }

    @Transactional
    public void failTransaction(String transactionKey) {
        try {
            TransactionControl transaction = transactionControlRepository.findByTransactionKey(transactionKey)
                    .orElseThrow(() -> new IllegalStateException(TRANSACTION_NOT_FOUND));

            transaction.setStatus(TransactionStatus.FAILED);
            transaction.setLastUpdated(LocalDateTime.now());
            transactionControlRepository.save(transaction);
        } catch (OptimisticLockingFailureException e) {
            throw new IllegalStateException(
                    TRANSACTION_WAS_MODIFIED_BY_ANOTHER_THREAD_PLEASE_TRY_AGAIN, e);
        }
    }

}