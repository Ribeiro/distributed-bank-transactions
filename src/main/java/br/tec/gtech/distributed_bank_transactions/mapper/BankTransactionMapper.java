package br.tec.gtech.distributed_bank_transactions.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import br.tec.gtech.distributed_bank_transactions.dto.BankTransactionDTO;
import br.tec.gtech.distributed_bank_transactions.entity.BankTransaction;

@Mapper(componentModel = "spring")
public interface BankTransactionMapper {

    @Mapping(target = "id", ignore = true)
    BankTransaction toEntity(BankTransactionDTO dto);

    BankTransactionDTO toDTO(BankTransaction entity);
}