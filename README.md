# 💰 Conversor de Moedas - Spring Boot API

## 📖 Descrição
Este projeto implementa um conversor de moedas com uma API RESTful em Spring Boot. A aplicação permite a conversão entre diferentes moedas, utilizando taxas de câmbio atualizadas obtidas de uma API externa. Além da conversão, o sistema também retorna a última data de atualização das taxas de câmbio, formatada no padrão brasileiro. 💱

## ✨ Funcionalidades
- 💵 **Conversão de Moedas**: Realiza conversões entre diversas moedas, como BRL, USD, EUR, ARS, etc.
- 🔄 **Taxas de Câmbio Dinâmicas**: Obtém as taxas de câmbio em tempo real, com base na moeda de origem fornecida.
- 📅 **Data de Atualização**: Exibe a última data de atualização das taxas de câmbio, formatada no padrão brasileiro.
- 🌐 **Suporte a CORS**: A API está configurada para permitir requisições de diferentes origens (Cross-Origin Resource Sharing).
- 📜 **Documentação da API**: A API possui documentação gerada automaticamente com o Spring Fox Swagger.

## 🛠 Tecnologias Utilizadas
- ☕ **Java 11**
- 🚀 **Spring Boot**
- 🧪 **JUnit 5** para testes automatizados
- 🛡️ **Mockito** para mocking de dependências nos testes
- 📦 **Gson** para manipulação de JSON
- 🌐 **CORS** para permitir requisições de diferentes origens
- 📖 **Swagger** para documentação da API

## 🔗 Endpoints
- **POST /api/conversor/converter**: Realiza a conversão entre duas moedas, aceitando o valor e as moedas de origem e destino.
  
  **Exemplo de corpo da requisição**:
  ```json
  {
    "valor": 100.0,
    "moedaOrigem": "BRL",
    "moedaDestino": "USD"
  }
  ```
  **Exemplo de resposta**:
  ```json
  {
    "valorConvertido": 16.53,
    "ultimaAtualizacao": "16/01/2025 00:00:01"
  }
  ```
