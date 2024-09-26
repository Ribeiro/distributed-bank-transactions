package br.tec.gtech.distributed_bank_transactions.distributed.generator;

import java.lang.reflect.Field;
import org.springframework.stereotype.Component;
import br.tec.gtech.distributed_bank_transactions.exception.InvalidBankTransactionException;

@Component
public class TransactionKeyGenerator {

    private static final String TRANSACTION_KEY = "transactionKey";

    public String generateKey(Object entity) {
        StringBuilder keyBuilder = new StringBuilder();

        Field[] fields = entity.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (TRANSACTION_KEY.equals(field.getName())) {
                continue;
            }

            try {
                Object value = field.get(entity);
                keyBuilder.append(value != null ? value.toString() : "null").append(";");
            } catch (IllegalAccessException e) {
                throw new InvalidBankTransactionException("Failed to access field: " + field.getName(), e);
            }
        }

        return String.valueOf(keyBuilder.toString().hashCode());
    }
}

