
# 💳Sistema-Controle-Financeiro
O projeto escolhido tem como objetivo implementar uma API em Java que utilize Spring Boot e o banco de dados MySql.
 uma API REST desenvolvida em Java com Spring Boot e MySQL. Seu objetivo é permitir o cadastro de usuários, contas, categorias e lançamentos, facilitando o registro e a consulta de receitas e despesas.

## 🗿Integrantes ​

- Ester Soares Serfaim
- Gabriel Soero
- João Victor Ressoni
- Aruã Paulo
- Mateus Lucas

## 📄​Requisitos do projeto

- **Tecnologias:** Java 21, Spring Boot, Maven e MySQL.
- **Arquitetura:** organização em camadas, no mínimo quatro entidades relevantes e pelo menos dois tipos de relacionamento entre entidades.
- **Funcionalidades:** operações CRUD, regras de negócio, filtros ou consultas personalizadas, paginação e ordenação.
- **Validação e segurança:** DTOs de requisição e resposta, validação de dados, tratamento centralizado de exceções, autenticação e controle básico de acesso.
- **Testes e documentação:** testes unitários com JUnit e Mockito, documentação OpenAPI/Swagger e coleção de requisições ou documentação equivalente.
- **Entrega:** controle de versão com Git, instruções de instalação e execução neste README, apresentação e demonstração da aplicação.
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
