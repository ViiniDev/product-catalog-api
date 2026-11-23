# Product Catalog API

[![CI](https://github.com/ViiniDev/product-catalog-api/actions/workflows/ci.yml/badge.svg)](https://github.com/ViiniDev/product-catalog-api/actions/workflows/ci.yml)

API REST de catálogo de produtos com cache Redis, criada para demonstrar performance, paginação e ambiente conteinerizado.

## Funcionalidades

- Cadastro e listagem de categorias.
- Cadastro e busca paginada de produtos.
- Filtro de produtos por nome.
- Cache nas consultas de produtos.
- Invalidação de cache ao cadastrar novo produto.
- Execução local com H2.
- Execução conteinerizada com PostgreSQL e Redis.
- Documentação via Swagger UI.
- Testes de integração para categoria, produto, busca paginada e validação.
- CI com GitHub Actions para testes e build Docker.

## Tecnologias

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Cache
- Redis
- PostgreSQL
- H2 Database
- Docker e Docker Compose
- Swagger/OpenAPI
- JUnit e MockMvc
- GitHub Actions
- Maven

## Como Executar Localmente

```bash
mvn clean package
java -jar target/product-catalog-api-0.0.1-SNAPSHOT.jar
```

Swagger:

```text
http://localhost:8080/docs
```

## Como Executar com Docker

```bash
cp .env.example .env
docker compose up --build
```

## Endpoints Principais

```http
POST /api/categories
GET /api/categories
POST /api/products
GET /api/products?search=notebook&page=0&size=10
```

## Exemplos

Criar categoria:

```json
{
  "name": "Notebooks"
}
```

Criar produto:

```json
{
  "name": "Notebook Pro",
  "description": "Notebook para desenvolvimento backend",
  "price": 4500.00,
  "stock": 8,
  "categoryId": 1
}
```

## Aprendizados Demonstrados

- Uso de cache com Spring Cache.
- Redis como infraestrutura auxiliar.
- Paginação com Spring Data.
- Invalidação de cache ao alterar dados.
- API, banco e cache subindo juntos com Docker Compose.
- Testes de integração para fluxo de catálogo.
- Pipeline de CI para validar testes e build Docker.

## Testes e CI

```bash
mvn test
```

Os testes validam:

- criação e listagem de categorias;
- criação de produto associado a categoria;
- busca paginada de produtos por nome;
- validação de preço inválido;
- inicialização do contexto Spring.

O workflow em `.github/workflows/ci.yml` executa os testes Maven e o build Docker via Docker Compose.
