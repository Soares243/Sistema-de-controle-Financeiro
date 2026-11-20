# Sistema de controle financeiro

API em Java com Spring Boot e MySQL para gerenciar usuarios, contas, categorias e lancamentos financeiros.

## Funcionalidades

- Cadastro, login e autenticacao com JWT.
- CRUD de usuarios, contas, categorias e lancamentos.
- Filtros, paginacao e ordenacao dos lancamentos.
- Validacao dos dados e protecao dos endpoints.

## Tecnologias

- JDK 21 a 25
- Maven
- MySQL

## Como executar no Windows

1. Inicie o MySQL e crie o banco:

```sql
CREATE DATABASE controle_financeiro;
```

2. Configure o Java na terminal:

```powershell
$env:JAVA_HOME="C:\Program Files\Java\jdk-25.0.5.0.2"
$env:Path="$env:JAVA_HOME\bin;$env:Path"
```

3. Na pasta do projeto, configure o banco e execute:

```powershell
$env:DB_USER="root"
$env:DB_PASSWORD="sua_senha"
$env:DB_URL="jdbc:mysql://localhost:3306/controle_financeiro?createDatabaseIfNotExist=true&serverTimezone=UTC"
$env:JWT_SECRET="uma-chave-local-com-pelo-menos-32-caracteres"
mvn spring-boot:run
```

O servidor sera iniciado em http://localhost:8080.

## Como usar

Abra o Swagger em http://localhost:8080/swagger-ui.html.

1. Execute `POST /api/auth/cadastro` com nome, e-mail e senha de pelo menos 8 caracteres.
2. Execute `POST /api/auth/login`.
3. Copie o token JWT e selecione **Authorize** no Swagger.
4. Cadastre uma conta e uma categoria.
5. Use os IDs cadastrados para criar receitas e despesas.

Exemplo de cadastro:

```json
{
  "nome": "Ana",
  "email": "ana@example.com",
  "senha": "senha123"
}
```

## Testes

```powershell
mvn test
```

O Swagger em formato JSON tambem fica disponivel em http://localhost:8080/v3/api-docs.
