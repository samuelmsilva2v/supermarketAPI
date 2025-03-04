# API Supermercado
![GitHub repo size](https://img.shields.io/github/repo-size/samuelmsilva2v/supermarketAPI?style=for-the-badge)
![GitHub language count](https://img.shields.io/github/languages/count/samuelmsilva2v/supermarketAPI?style=for-the-badge)
![GitHub forks](https://img.shields.io/github/forks/samuelmsilva2v/supermarketAPI?style=for-the-badge)
![Bitbucket open issues](https://img.shields.io/bitbucket/issues/samuelmsilva2v/supermarketAPI?style=for-the-badge)
![Bitbucket open pull requests](https://img.shields.io/bitbucket/pr-raw/samuelmsilva2v/supermarketAPI?style=for-the-badge)

[🇺🇸 Read in English](#supermarket-api)

🛒 API RESTful desenvolvida em **Java** e **Spring Boot** para controle e gerenciamento de produtos de um supermercado, permitindo operações de CRUD (Create, Read, Update, Delete).

O sistema garante que as regras de negócio sejam seguidas, como a obrigatoriedade de categorias, prevenção de cadastro duplicado e restrições na exclusão de produtos com estoque.

## Funcionalidades
* Cadastrar novos produtos com os seguintes atributos:
  * Nome
  * Preço
  * Quantidade em estoque
  * Categoria (ex: Bebidas, Hortifruti, Padaria, etc.)

* Listar todos os produtos cadastrados.

* Editar informações de um produto existente.

* Excluir produtos, desde que o estoque seja igual a 0.

### Regras de negócio
* Não pode cadastrar produtos com o mesmo nome.

* Não pode excluir um produto que tenha estoque maior que 0.

* O preço do produto não pode ser negativo.

* Ao cadastrar ou editar um produto, é obrigatório informar uma categoria.

## Tecnologias Utilizadas

* Java 21
* Spring Boot
* Spring Data JPA
* Hibernate
* PostgreSQL
* Docker (para virtualização do banco de dados)
* JUnit (para testes unitários)
* Swagger (para documentação)

## Endpoints

- #### Produtos
| Método | Endpoint             | Descrição                         |
|--------|----------------------|-----------------------------------|
| POST   | `/api/produtos`      | Cadastra um novo produto          |
| PUT    | `/api/produtos/{id}` | Edita os dados de um produto      |
| GET    | `/api/produtos`      | Consulta todos os produtos        |
| GET    | `/api/produtos/{id}` | Consulta um produto através do ID |
| DELETE | `/api/produtos/{id}` | Exclui um produto                 |

- #### Categorias
| Método | Endpoint               | Descrição                            |
|--------|------------------------|--------------------------------------|
| POST   | `/api/categorias`      | Cadastra uma nova categoria          |
| PUT    | `/api/categorias/{id}` | Edita os dados de uma categoria      |
| GET    | `/api/categorias`      | Consulta todas as categorias         |
| GET    | `/api/categorias/{id}` | Consulta uma categoria através do ID |
| DELETE | `/api/categorias/{id}` | Exclui uma categoria                 |

## Instalação e Configuração
**1. Clonar o Repositório**
```bash
 git clone https://github.com/samuelmsilva2v/supermarketAPI.git
 cd supermarketAPI
```

**2. Virtualizar o Banco de Dados no Docker**
```bash
docker-compose up -d
```

**3. Executar o Back-end**
```bash
mvn spring-boot:run
```
A API estará disponível em http://localhost:8080/swagger-ui/index.html#/.

### Testes
Para rodar os testes automatizados
```bash
mvn test
```

---

# Supermarket API
[🇧🇷 Leia em Português](#api-supermercado)

🛒 RESTful API developed in Java and Spring Boot for controlling and managing supermarket products, allowing CRUD operations (Create, Read, Update, Delete).

The system ensures that business rules are followed, such as mandatory categories, prevention of duplicate registrations, and restrictions on deleting products with stock.

## Features
* Register new products with the following attributes:
  * Name
  * Price
  * Stock quantity
  * Category (e.g., Beverages, Produce, Bakery, etc.)

* List all registered products.

* Edit information of an existing product.

* Delete products, as long as the stock is equal to 0.

### Business Rules
* Products with the same name cannot be registered.

* A product cannot be deleted if its stock is greater than 0.

* The product price cannot be negative.

* When registering or editing a product, a category must be provided.

## Technologies Used

* Java 21
* Spring Boot
* Spring Data JPA
* Hibernate
* PostgreSQL
* Docker (for database virtualization)
* JUnit (for unit testing)
* Swagger (for documentation)

## Endpoints

- #### Products
| Method | Endpoint             | Description               |
|--------|----------------------|---------------------------|
| POST   | `/api/produtos`      | Registers a new product   |
| PUT    | `/api/produtos/{id}` | Edits product data        |
| GET    | `/api/produtos`      | Retrieves all products    |
| GET    | `/api/produtos/{id}` | Retrieves a product by ID |
| DELETE | `/api/produtos/{id}` | Deletes a product         |

- #### Categories
| Method | Endpoint               | Description                |
|--------|------------------------|----------------------------|
| POST   | `/api/categorias`      | Registers a new category   |
| PUT    | `/api/categorias/{id}` | Edits category data        |
| GET    | `/api/categorias`      | Retrieves all categories   |
| GET    | `/api/categorias/{id}` | Retrieves a category by ID |
| DELETE | `/api/categorias/{id}` | Deletes a category         |

## Installation and Configuration
**1. Clone the Repository**
```bash
 git clone https://github.com/samuelmsilva2v/supermarketAPI.git
 cd supermarketAPI
```

**2. Virtualize the Database in Docker**
```bash
docker-compose up -d
```

**3. Run the Back-end**
```bash
mvn spring-boot:run
```
The API will be available at http://localhost:8080/swagger-ui/index.html#/.

### Testing
To run automated tests
```bash
mvn test
```
