<h1 align="center">
  👤 <a href="#" alt="site do CRUD Customers"> CRUD Customers </a>
</h1>

<h2 align="center">
   Com o CRUD Customers você controla todo o fluxo de clientes (PF ou PJ).
</h2>

## [](https://github.com/diegofortunato/crud-customers#stack-)Stack  💻

-   Kotlin
-   Spring Boot
-   H2
-   Docker

## [](https://github.com/diegofortunato/crud-customers#build--)Build  🚀

Certifique que você tenha o Docker instalado em sua maquina e rode os seguintes comandos:

-   Primeiro passo clone o projeto:  `https://github.com/diegofortunato/crud-customers`
-   Entre na pasta raiz no local clonado.
-   Build o projeto com:  `gradle clean build`
-   Após o build execute o seguinte comando:  `docker build -t app.jar .`
-   Após esse comando. execute:  `docker-compose up`

Pronto, o projeto estara disponivel em:  `localhost:8080`

## [](https://github.com/diegofortunato/crud-customers#documeta%C3%A7%C3%A3o-)Documetação  📝

Você pode encontrar a documentação do projeto aqui: [Swagger-UI](http://localhost:8080/swagger-ui.html#/)
                                                    [Doc](https://github.com/diegofortunato/crud-customers/blob/master/docs/swagger.yaml)
                                                    
## [](https://github.com/diegofortunato/crud-customers#documeta%C3%A7%C3%A3o-)Health Check  🏥

Saúde da API esta disponivel aqui:  [Actuator](http://localhost:8080/actuator/health)

## [](https://github.com/diegofortunato/crud-customers#autor-)Autor  🦸

Diego Fortunato Candido
