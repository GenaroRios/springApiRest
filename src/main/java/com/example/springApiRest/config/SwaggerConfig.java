package com.example.springApiRest.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuracion Swagger para la generacion de documentacion de la API REST
 *
 * http://localhost:8080/swagger-ui/
 * Para acceeder a la documentacion
 */

@Configuration //Notacion para que el compilador detecte que esta clase es una configuration
public class SwaggerConfig {

    @Bean //Notacion para que se cree un bean a partir de este objeto
    public OpenAPI customOpenApi (){
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Boot API REST")
                        .version("1.0")
                        .description("Library Api Rest Docs")
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                        .contact(new Contact().name("Rios Genaro").url("https://github.com/GenaroRios").email("genarios2002@gmail.com")));
    }
}
