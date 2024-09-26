package br.tec.gtech.distributed_bank_transactions.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.tec.gtech.distributed_bank_transactions.dto.BankTransactionDTO;
import br.tec.gtech.distributed_bank_transactions.mapper.BankTransactionMapper;
import br.tec.gtech.distributed_bank_transactions.service.BankTransactionService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/bank-transactions")
@AllArgsConstructor
public class BankTransactionController {

    private final BankTransactionService bankTransactionService;

    private final BankTransactionMapper bankTransactionMapper;

    @PostMapping("/create")
    public ResponseEntity<String> createTransaction(@RequestBody BankTransactionDTO dto) {
        try {
            bankTransactionService.createTransaction(bankTransactionMapper.toEntity(dto));
            return ResponseEntity.ok("Transaction successful");
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}