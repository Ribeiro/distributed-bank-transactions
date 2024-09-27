package br.tec.gtech.distributed_bank_transactions.distributed.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.tec.gtech.distributed_bank_transactions.distributed.entity.TransactionControl;

@Repository
public interface TransactionControlRepository extends JpaRepository<TransactionControl, Long> {
    Optional<TransactionControl> findByTransactionKey(String transactionKey);
}