package br.tec.gtech.distributed_bank_transactions.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import br.tec.gtech.distributed_bank_transactions.distributed.generator.TransactionKeyGenerator;
import br.tec.gtech.distributed_bank_transactions.enums.TransactionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bank_transaction", uniqueConstraints = {@UniqueConstraint(columnNames = {"account_id", "transaction_date"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankTransaction {

    private TransactionKeyGenerator transactionKeyGenerator;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Column(name = "transaction_amount", nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    private TransactionType type;

    @Column(name = "transaction_date", nullable = false)
    private LocalDateTime date;

    @Transient
    private String transactionKey;

    @PrePersist
    protected void onCreate() {
        if (this.date == null) {
            this.date = LocalDateTime.now();
        }
        this.transactionKey = transactionKeyGenerator.generateKey(this);
    }
}