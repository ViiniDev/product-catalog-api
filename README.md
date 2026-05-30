# Product Catalog API

[![CI](https://github.com/ViiniDev/product-catalog-api/actions/workflows/ci.yml/badge.svg)](https://github.com/ViiniDev/product-catalog-api/actions/workflows/ci.yml)

API REST de catalogo de produtos com cache Redis, criada para demonstrar performance, paginacao e ambiente conteinerizado.

## Funcionalidades

- Cadastro e listagem de categorias.
- Cadastro e busca paginada de produtos.
- Filtro de produtos por nome.
- Cache nas consultas de produtos.
- Invalidacao de cache ao cadastrar novo produto.
- Execucao local com H2.
- Execucao conteinerizada com PostgreSQL e Redis.
- Documentacao via Swagger UI.
- Testes de integracao para categoria, produto, busca paginada e validacao.
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
- Paginacao com Spring Data.
- Invalidacao de cache ao alterar dados.
- API, banco e cache subindo juntos com Docker Compose.
- Testes de integracao para fluxo de catalogo.
- Pipeline de CI para validar testes e build Docker.

## Testes E CI

```bash
mvn test
```

Os testes validam:

- criacao e listagem de categorias;
- criacao de produto associado a categoria;
- busca paginada de produtos por nome;
- validacao de preco invalido;
- inicializacao do contexto Spring.

O workflow em `.github/workflows/ci.yml` executa os testes Maven e o build Docker via Docker Compose.
