```markdown
# Distributed Bank Transactions

Este projeto é uma implementação de um sistema de controle de transações bancárias distribuídas utilizando Spring Boot, Lombok e PostgreSQL. O sistema é projetado para garantir a integridade das transações em ambientes com múltiplas réplicas, utilizando otimização de bloqueio (Optimistic Locking).

## Tecnologias Utilizadas

- **Java 21**: Linguagem de programação utilizada.
- **Spring Boot**: Framework para construção de aplicações Java.
- **Lombok**: Biblioteca para reduzir o boilerplate de código.
- **PostgreSQL**: Sistema de gerenciamento de banco de dados relacional.
- **MapStruct**: Para mapeamento entre DTOs e entidades.

## Funcionalidades

- Controle de transações bancárias com status:
  - Em andamento
  - Completa
  - Falha
- Geração de chaves únicas para controle de transações.
- Suporte a múltiplas réplicas sem duplicação de registros.
- Tratamento de exceções para conflitos de otimização.

## Estrutura do Projeto

```
src
├── main
│   ├── java
│   │   └── br
│   │       └── tec
│   │           └── gtech
│   │               └── distributed_bank_transactions
│   │                   ├── controller
│   │                   ├── distributed
│   │                   │   ├── entity
│   │                   │   ├── enums
│   │                   │   ├── repository
│   │                   │   └── service
│   │                   ├── entity
│   │                   ├── enums
│   │                   ├── mapper
│   │                   ├── dto
│   │                   ├── exception
│   │                   └── DistributedBankTransactionsApplication.java
│   └── resources
│       ├── application.properties
│       └── schema.sql
└── test
    └── java
        └── br
            └── tec
                └── gtech
                    └── distributed_bank_transactions
```

## Configuração do Banco de Dados

1. Certifique-se de ter o PostgreSQL instalado e rodando.
2. Crie um banco de dados chamado `bank_transactions`.
3. Importe o esquema de banco de dados fornecido em `src/main/resources/schema.sql`.

### Exemplo de `application.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/bank_transactions
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

## Execução do Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu_usuario/distributed-bank-transactions.git
   ```

2. Navegue até o diretório do projeto:
   ```bash
   cd distributed-bank-transactions
   ```

3. Compile e execute a aplicação:
   ```bash
   ./mvnw spring-boot:run
   ```

4. A aplicação estará disponível em `http://localhost:8080`.

## Endpoints

### Criar Transação Bancária

```http
POST /api/transactions
Content-Type: application/json

{
    "accountId": 1,
    "transactionAmount": 100.00,
    "transactionType": "DEPOSIT"
}
```

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir um issue ou enviar um pull request.

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).
```

### Notas

- Substitua os placeholders, como `seu_usuario`, `sua_senha`, e `sua_chave_transacao`, conforme necessário.
- Adapte a seção de **Endpoints** para refletir os endpoints reais que você implementou no seu controlador.
- Inclua informações adicionais relevantes, como instruções de configuração, se necessário.

Se precisar de mais alguma coisa, é só avisar!
