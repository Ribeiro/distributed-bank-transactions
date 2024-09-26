package br.tec.gtech.distributed_bank_transactions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.tec.gtech.distributed_bank_transactions.entity.BankTransaction;

public interface BankTransactionRepository extends JpaRepository<BankTransaction, Long> {
}