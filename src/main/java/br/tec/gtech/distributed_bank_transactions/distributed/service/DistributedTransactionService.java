package br.tec.gtech.distributed_bank_transactions.distributed.service;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.tec.gtech.distributed_bank_transactions.distributed.entity.TransactionControl;
import br.tec.gtech.distributed_bank_transactions.distributed.enums.TransactionStatus;
import br.tec.gtech.distributed_bank_transactions.distributed.repository.TransactionControlRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DistributedTransactionService {

    private final TransactionControlRepository transactionControlRepository;

    @Transactional
    public void beginTransaction(String transactionKey) {
        Optional<TransactionControl> existingTransaction = transactionControlRepository.findByTransactionKey(transactionKey);
        if (existingTransaction.isPresent()) {
            TransactionControl transaction = existingTransaction.get();
            if(transaction.getStatus() == TransactionStatus.COMPLETED){
                throw new IllegalStateException("Transaction already completed");
            }
            // A transação já está em progresso
        } else {
            // Criar nova transação
            TransactionControl newTransaction = new TransactionControl();
            newTransaction.setTransactionKey(transactionKey);
            newTransaction.setStatus(TransactionStatus.IN_PROGRESS);
            newTransaction.setLastUpdated(LocalDateTime.now());
            transactionControlRepository.save(newTransaction);
        }
    }

    @Transactional
    public void completeTransaction(String transactionKey) {
        TransactionControl transaction = transactionControlRepository.findByTransactionKey(transactionKey)
                .orElseThrow(() -> new IllegalStateException("Transaction not found"));

        transaction.setStatus(TransactionStatus.COMPLETED);
        transaction.setLastUpdated(LocalDateTime.now());
        transactionControlRepository.save(transaction);
    }

    @Transactional
    public void failTransaction(String transactionKey) {
        TransactionControl transaction = transactionControlRepository.findByTransactionKey(transactionKey)
                .orElseThrow(() -> new IllegalStateException("Transaction not found"));

        transaction.setStatus(TransactionStatus.FAILED);
        transaction.setLastUpdated(LocalDateTime.now());
        transactionControlRepository.save(transaction);
    }
}

