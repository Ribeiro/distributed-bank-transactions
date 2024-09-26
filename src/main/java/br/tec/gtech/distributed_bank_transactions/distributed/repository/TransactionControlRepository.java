package br.tec.gtech.distributed_bank_transactions.distributed.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.tec.gtech.distributed_bank_transactions.distributed.entity.TransactionControl;

@Repository
public interface TransactionControlRepository extends JpaRepository<TransactionControl, Long> {

    @Lock(jakarta.persistence.LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT t FROM TransactionControl t WHERE t.transactionKey = :transactionKey")
    Optional<TransactionControl> findByTransactionKeyWithLock(String transactionKey);
}
