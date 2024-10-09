## Spring boot

Proyecto Spring boot con las dependencias / starters:

* H2
* Spring Data JPA
* Spring Web
* Spring Boot Dev Tools

Aplicacion API REST con acceso a BDD H2 para persistir la informacion.

El acceso se puede realizar desde postman o navegador.

## Entidad Book

1. Book
2. BookRepository
3. BookController

## Funciones CRUD

BookController contiene las funciones para el acceso a la informacion de la BDD.

* GET /api/books
* GET /api/book/{id}
* POST /api/book/new
* PUT /api/book/update
* DELETE /api/book/{id}

## Documentacion Swagger

Mediante spring-doc Openapi se realizo la configuracion de una pagina web con la documentacion correspondiente.

1. SwaggerConfig
2. application.properties