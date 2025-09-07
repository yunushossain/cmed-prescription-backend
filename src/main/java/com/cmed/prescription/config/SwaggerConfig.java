package com.cmed.prescription.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI apiDoc() {
        return new OpenAPI().info(new Info().title("Prescription API").version("v1")
                .description("Endpoints for managing prescriptions and reports"));
    }
}
