package com.guidosit.gamexchange.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
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
public class SwaggerConfig extends WebMvcConfigurationSupport {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.guidosit.gamexchange"))
                .paths(PathSelectors.any())
                .build().apiInfo(informacoesApi());

    }
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    private ApiInfo informacoesApi() {

        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();

        apiInfoBuilder.title("GameXchange API");
        apiInfoBuilder.description("API services for GameXchange application");
        apiInfoBuilder.version("1.0");
        apiInfoBuilder.termsOfServiceUrl("All rights reserved");
        apiInfoBuilder.license("private");
        apiInfoBuilder.licenseUrl("");
        apiInfoBuilder.contact(this.contato());

        return apiInfoBuilder.build();

    }
    private Contact contato() {

        return new Contact(
                "Thiago Gonçalves da Silva",
                "",
                "thigonsilva@gmail.com");
    }
}
