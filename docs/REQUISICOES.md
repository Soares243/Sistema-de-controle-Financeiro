# Requisições e roteiro de apresentação — AP1

No Swagger UI, faça as operações nesta ordem. Os IDs abaixo são exemplos de um banco vazio; use os IDs retornados no seu ambiente.

1. **POST /api/usuarios**: `{"nome":"Ana Silva","email":"ana@example.com"}`. Liste, consulte pelo ID, altere com PUT e observe os dados retornados.
2. **POST /api/contas**: `{"nome":"Conta corrente","saldoInicial":1000.00,"usuarioId":1}`.
3. **POST /api/categorias**: `{"nome":"Alimentação","tipo":"DESPESA"}`.
4. **POST /api/lancamentos**: `{"descricao":"Compra no mercado","valor":75.90,"data":"2026-09-23","tipo":"DESPESA","contaId":1,"categoriaId":1}`.
5. **GET /api/lancamentos?contaId=1&page=0&size=10&sort=data&direction=desc**: demonstre filtro, paginação e ordenação.
6. Demonstre validação: tente criar lançamento com `"valor":0` (HTTP 400), ou com `"tipo":"RECEITA"` usando a categoria de DESPESA (HTTP 409).
7. Faça **PUT /api/lancamentos/1** com `{"descricao":"Mercado do mês","valor":80.00,"data":"2026-09-23","tipo":"DESPESA","contaId":1,"categoriaId":1}`; consulte e exclua com DELETE; consulte novamente para mostrar HTTP 404.

Para demonstrar a exclusão das demais entidades, remova primeiro os lançamentos, depois categorias e contas, e por fim o usuário. A seção **Schemas** no Swagger documenta os campos e as validações de cada DTO.

## Sugestão de fala para apresentação (3–5 minutos)

1. Problema: registrar entradas e saídas financeiras por conta e categoria.
2. Arquitetura: Controller recebe HTTP, DTO valida, Service aplica regras, Repository grava no MySQL.
3. Entidades e vínculos: usuário → contas; lançamento → conta e categoria.
4. Demonstração ao vivo: cadastre os quatro registros acima e filtre os lançamentos.
5. Teste de erro: valor zero e categoria incompatível; mostre respostas HTTP 400 e 409.
6. Próxima etapa: autenticação e autorização, relatórios e ampliação dos testes.
