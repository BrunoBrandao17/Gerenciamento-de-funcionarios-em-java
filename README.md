# 👥 API de gerenciamento de funcionários

Esta é uma API robusta para o gerenciamento de funcionários, focada em segurança e integridade de dados. O projeto vai além de um CRUD simples, implementando camadas de segurança e padrões de transferência de dados (DTO) utilizados em grandes aplicações corporativas.

## 🚀 Diferenciais Técnicos

Este projeto demonstra maturidade técnica ao implementar:
* **Segurança com BCrypt:** As senhas dos funcionários não são salvas como texto simples, mas criptografadas antes de irem para o banco de dados.
* **Padrão DTO:** Separação total entre a entidade do banco de dados (`Model`) e os dados que trafegam na rede (`Request/Response DTO`), garantindo que informações sensíveis não sejam expostas.
* **Bean Validation:** Uso de anotações como `@NotBlank`, `@Email` e `@Size` para validar os dados no momento da requisição.
* **Configuração de Segurança:** Implementação de `SecurityFilterChain` para controle de acesso aos endpoints.

## 🛠️ Tecnologias

* **Java**
* **Spring Boot 3**
* **Spring Security** (Criptografia e Configuração)
* **Spring Data JPA**
* **Validation API** (Hibernate Validator)
* **MySQL**

## 🏗️ Arquitetura do Sistema

O projeto segue uma estrutura organizada e desacoplada:
* **Controllers:** Endpoints validados com `@Valid`.
* **DTOs:** Objetos de transferência para entrada e saída de dados.
* **Services:** Lógica de negócio e integração com segurança.
* **Repositories:** Abstração da camada de dados com MySQL.
* **Exception Handling:** Tratamento global de conflitos (ex: e-mail duplicado) e erros de validação.

## ⚙️ Como Executar

Clonar o Repositório
Configure o banco de dados no application.properties
Configurar o Banco de Dados (CREATE DATABASE gerenciamentoFuncionarios;)
Executar: mvn spring-boot:run
Desenvolvido por Bruno Machado Brandão 📩Brunomachad17@gmail.com 🚀
