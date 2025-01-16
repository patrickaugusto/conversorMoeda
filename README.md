# Conversor de Moedas - Spring Boot API

## Descrição
Este projeto implementa um conversor de moedas com uma API RESTful em Spring Boot. A aplicação permite a conversão entre diferentes moedas, utilizando taxas de câmbio atualizadas obtidas de uma API externa. Além da conversão, o sistema também retorna a última data de atualização das taxas de câmbio.

## Funcionalidades
- Conversão de moedas: Realiza conversões entre diversas moedas, como BRL, USD, EUR, ARS, etc.
- Taxas de câmbio dinâmicas: Obtém as taxas de câmbio em tempo real.
- Data de atualização: Exibe a última data de atualização das taxas de câmbio.


## Tecnologias Utilizadas
- **Java 11**
- **Spring Boot**
- **JUnit 5** para testes automatizados.
- **Mockito** para mocking de dependências.
- **Gson** para manipulação de JSON.

## Endpoints
- **GET /converter**: Realiza a conversão entre duas moedas.
