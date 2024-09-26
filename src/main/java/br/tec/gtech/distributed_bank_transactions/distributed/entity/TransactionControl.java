package br.tec.gtech.distributed_bank_transactions.distributed.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import br.tec.gtech.distributed_bank_transactions.distributed.enums.TransactionStatus;

@Entity
@Table(name = "transaction_control")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionControl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_key", nullable = false, unique = true)
    private String transactionKey;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus status; 

    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;
}
