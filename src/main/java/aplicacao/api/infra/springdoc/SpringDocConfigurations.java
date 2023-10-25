package aplicacao.api.infra.springdoc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SpringDocConfigurations {

	    @Bean
	     public OpenAPI customOpenAPI() {
	         return new OpenAPI()
	                        .components(new Components()
	                            .addSecuritySchemes("bearer-key",
	                            new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
	                        .info(new Info()
	                                .title("API Cadastro de usuarios")
	                                .description("API Rest da aplicação Cadastro.usuario contendo as funcionalidades de CRUD de usuarios, além de cadastrar, listar e excluir.")
	                                .contact(new Contact()
	                                        .name("Time Backend")
	                                        .email("backend@voll.med"))
	                        .license(new License()
	                                .name("Apache 2.0")
	                                .url("http://cadastro.usuario/api/licenca")));
	    }
	
}



