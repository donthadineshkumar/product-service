package com.contentserv.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.contentserv.controller"))
                .paths(regex(".*"))
                .build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "Product Service",
                "Product related admin & user features",
                "1.0",
                "Terms of service url",
                new Contact("Administrator", "https://www.contentserv.com",
                        "admin@contentserv.com"),
                "Apache Licence Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0"
        );
        return apiInfo;
    }
}
