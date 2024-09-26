package br.tec.gtech.distributed_bank_transactions.exception;

public class InvalidBankTransactionException extends RuntimeException{

    public InvalidBankTransactionException(String message){
        super(message);
    }

    public InvalidBankTransactionException(String message, Throwable cause){
        super(message, cause);
    }
}
