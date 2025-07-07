package com.giordanobrunomichela.final_test.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for OpenAPI documentation (Swagger).
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Final Test API", version = "v1")
)

public class OpenApiConfig {
}