# SISTEMA DE CONTROLE FINANCEIRO

**Atividade:** Avaliação de Produto 1 (AP1)  
**Componentes do grupo:** [PREENCHER NOMES COMPLETOS ANTES DE ENTREGAR]

## Objetivo e escopo

API REST para cadastrar usuários, contas, categorias e lançamentos de receitas e despesas. Esta entrega contempla **a primeira etapa**: CRUD nas quatro entidades, interface OpenAPI/Swagger, validação de entrada e organização MVC em camadas. Também há uma regra de negócio, consulta filtrada e teste unitário. A autenticação e o controle de acesso, exigidos para o **projeto completo**, ficam para a etapa seguinte. **Não use esta versão com dados financeiros reais em um servidor público:** os endpoints ainda não exigem autenticação.

## Tecnologias

- Java 21; Spring Boot 3.5.7; Maven; Spring Web; Spring Data JPA; Bean Validation.
- MySQL 8 ou superior; springdoc-openapi 2.8.13 (Swagger UI).
- JUnit 5 e Mockito para testes unitários.

## Executar

1. Instale **JDK 21**, Maven 3.6.3 ou superior e MySQL. Confira com `java -version` e `mvn -version`.
2. Inicie o MySQL e configure `DB_USER`, `DB_PASSWORD` e, se necessário, `DB_URL`. O padrão é `jdbc:mysql://localhost:3306/controle_financeiro?createDatabaseIfNotExist=true&serverTimezone=UTC`. O usuário precisa de permissão para criar o banco ou, alternativamente, crie-o manualmente com `CREATE DATABASE controle_financeiro;`.
3. Na pasta que contém `pom.xml`, execute `mvn test` e `mvn spring-boot:run`.
4. Abra `http://localhost:8080/swagger-ui.html`. A especificação JSON está em `http://localhost:8080/v3/api-docs`.

No PowerShell, por exemplo: `$env:DB_USER="root"; $env:DB_PASSWORD="sua_senha"; mvn spring-boot:run`.
O projeto usa `ddl-auto=update` apenas para facilitar a demonstração acadêmica; em produção, use migrações versionadas.

## Estrutura MVC e responsabilidades

| Pasta | Responsabilidade |
| --- | --- |
| `model` | Entidades JPA e enum de receita/despesa |
| `repository` | Persistência e consultas Spring Data |
| `service` | Regras de negócio e transações |
| `controller` | Requisições HTTP e respostas REST |
| `dto` | Contratos de entrada e saída, sem expor entidades |
| `error` | Exceções e respostas de erro centralizadas |

Relacionamentos: um usuário tem várias contas (`@OneToMany`); uma conta pertence a um usuário (`@ManyToOne`); lançamentos pertencem a uma conta e a uma categoria (`@ManyToOne`). Os valores monetários usam `BigDecimal`.

## Endpoints e demonstração

Cada recurso possui `POST /api/{recurso}`, `GET /api/{recurso}`, `GET /api/{recurso}/{id}`, `PUT /api/{recurso}/{id}` e `DELETE /api/{recurso}/{id}`. Recursos: `usuarios`, `contas`, `categorias`, `lancamentos`. A criação retorna HTTP 201 e localização; exclusão retorna 204. Consulte [docs/REQUISICOES.md](docs/REQUISICOES.md) para exemplos completos e roteiro de apresentação.

Exemplo de consulta: `GET /api/lancamentos?contaId=1&page=0&size=10&sort=data&direction=desc`. Só `id`, `data`, `valor` e `descricao` são aceitos na ordenação. O valor deve ser positivo; o tipo do lançamento deve coincidir com o da categoria. Categorias, contas e usuários associados a registros dependentes não podem ser excluídos. O e-mail deve ser único. Erros de validação retornam HTTP 400, recursos inexistentes HTTP 404 e conflitos HTTP 409.

## Próxima etapa do projeto completo

Implementar Spring Security com senha cifrada e autenticação, autorização por usuário, testes de integração com MySQL, migrações Flyway e relatórios financeiros. Esta AP1 não afirma cumprir esses requisitos futuros.

## Versionamento

Projeto inicial preparado para Git. Após descompactar, execute `git init`, `git add .` e `git commit -m "Entrega AP1 controle financeiro"` se desejar criar seu próprio histórico.
