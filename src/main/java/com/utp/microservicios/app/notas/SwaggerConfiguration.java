package com.utp.microservicios.app.notas;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
@SecurityScheme(
        name = "Authorization",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SwaggerConfiguration {
	
	  @Bean
	    public Docket api(){
	        return new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("com.utp.microservicios.app.notas.controller"))
	                .paths(PathSelectors.any())
	                .build().apiInfo(apiEndPointInfo());
	    }

	    public ApiInfo apiEndPointInfo(){
	        return new ApiInfoBuilder().title("Spring Boot Rest API")
	                .description("Donor Management API")
	                .contact(new Contact("Francisco Julca", "medium.com/codefountain", "fjulcav@gmail.com"))
	                .license("Apache 2.0")
	                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
	                .version("0.0.1-SNAPSHOT")
	                .build();
	    }
}