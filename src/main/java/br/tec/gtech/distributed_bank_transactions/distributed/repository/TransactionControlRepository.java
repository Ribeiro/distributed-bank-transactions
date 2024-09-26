package br.tec.gtech.distributed_bank_transactions.distributed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.tec.gtech.distributed_bank_transactions.distributed.entity.TransactionControl;
import java.util.Optional;

public interface TransactionControlRepository extends JpaRepository<TransactionControl, Long> {
    Optional<TransactionControl> findByTransactionKey(String transactionKey);
}