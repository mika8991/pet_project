package ru.goodbadnews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class GoodbadnewsApplication {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SPRING_WEB.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ru.goodbadnews"))
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(GoodbadnewsApplication.class, args);
    }
}

