package com.example.inventory.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI inventoryOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Inventory Service API")
                        .version("1.0.0")
                        .description(
                                "REST API for inventory management (MongoDB). OpenAPI 3.0: /api-docs, Swagger UI: /swagger-ui.html. "
                                        + "Item id is a MongoDB ObjectId string. "
                                        + "Rate limiting: read=inventoryRead, write=inventoryWrite (see application.yml). "
                                        + "Throttling: bulkhead inventoryApi limits concurrent calls."))
                .servers(List.of(new Server().url("http://localhost:8080").description("Local")));
    }
}
