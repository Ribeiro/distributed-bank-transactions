package br.tec.gtech.distributed_bank_transactions.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import br.tec.gtech.distributed_bank_transactions.enums.TransactionType;

@Getter
@Setter
public class BankTransactionDTO {
    private Long accountId;
    private BigDecimal amount;
    private TransactionType type;
}