package com.synf.user.usermanagement.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan(basePackages = "com.synf.user")
public class SwaggerConfig {

	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("user-api").select().paths(PathSelectors.regex("/user.*")).build();
            
    }
	
    private ApiInfo apiEndPointsInfo() {
    	return new ApiInfo("User Management API",
                "APIs developed for the Image upload",
                "v1",
                "urn:tos",
                new Contact("Subramanyam Chinnam", "", "subbuchinnam@gmail.com"),
                "",
                "",
                new ArrayList<>());
    }
	
}
