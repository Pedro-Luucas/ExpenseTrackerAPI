# API DE FINANÇAS PESSOAIS

## Características do projeto:
* banco de dados Postgres
* autenticação de usuário(http basic)
* CRUD para despesas e rendas
* documentação com OpenAPI

## Instruções para a execução do projeto

* clone o repositório e execute [ExpenseTrackerApplication.java](src%2Fmain%2Fjava%2Fcom%2Fpedrao%2FExpenseTracker%2FExpenseTrackerApplication.java)
* de um POST localhost:8080/auth/register 
{
  "username": "admin",
  "password": "123",
  "balance": 0,
  "roles": ["ADMIN"]
  }
* abra no navegador http://localhost:8080/swagger-ui.html
* logue com usuário: "admin" e senha: "123"