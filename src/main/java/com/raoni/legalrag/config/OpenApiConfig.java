package com.raoni.legalrag.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    OpenAPI legalRagOpenApi() {
        return new OpenAPI().info(new Info()
                .title("Java Spring AI Document Platform")
                .version("0.1.0")
                .description("RAG document platform with AI safety, auditability, and LLMOps concepts."));
    }
}
