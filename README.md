
# Curso Spring Boot - API de Agendamento Médico

Este repositório contém o código de uma API de agendamento médico, desenvolvida como parte do aprendizado em **Spring Boot 3**. O projeto tem como objetivo consolidar conhecimentos em desenvolvimento backend, aplicando boas práticas de organização de código.

---

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Flyway** (migração de banco de dados)
- **MySQL** (persistência de dados)

---

## Funcionalidades da API

- Cadastro de médicos e pacientes.
- Agendamento de consultas médicas.
- Listagem e atualização de dados.
- Exclusão lógica para manter histórico.

---

## Estrutura do Projeto

- **Controller**: Ponto de entrada das requisições HTTP.
- **Service**: Regras de negócio e validações.
- **Repository**: Acesso e manipulação de dados no banco.
- **Entity**: Representações das tabelas do banco de dados.
- **DTOs**: Objetos para transferências de dados.

---

## Como Rodar o Projeto

1. Clone este repositório:
   ```bash
   git clone https://github.com/jrcosta/curso-springboot-alura.git
   ```
2. Configure o banco de dados MySQL no arquivo `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/nome_do_banco
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   ```
3. Execute as migrações do Flyway ao iniciar o projeto.

4. Inicie a aplicação:
   ```bash
   ./mvnw spring-boot:run
   ```

---

## Status

🚧 Projeto em desenvolvimento, criado com fins educacionais.
