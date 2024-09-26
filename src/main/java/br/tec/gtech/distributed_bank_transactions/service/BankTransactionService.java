package br.tec.gtech.distributed_bank_transactions.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.tec.gtech.distributed_bank_transactions.distributed.service.DistributedTransactionService;
import br.tec.gtech.distributed_bank_transactions.entity.BankTransaction;
import br.tec.gtech.distributed_bank_transactions.repository.BankTransactionRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BankTransactionService {

    private final BankTransactionRepository bankTransactionRepository;
    private final DistributedTransactionService distributedTransactionService;

    @Transactional
    public void createTransaction(BankTransaction bankTransaction) {
        String key = bankTransaction.getTransactionKey();
        // Inicia a transação distribuída
        distributedTransactionService.beginTransaction(key);

        bankTransactionRepository.save(bankTransaction);
        
        // Marca a transação como concluída
        distributedTransactionService.completeTransaction(key);
    }
}