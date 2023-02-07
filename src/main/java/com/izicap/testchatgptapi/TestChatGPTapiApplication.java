package com.izicap.testchatgptapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(title = "Ask ChatGPT API", version = "1.0"),
        servers = { @Server(url = "http://localhost:8080") },
        tags = { @Tag(name = "AskQuestion", description = "Mappings to Ask ChatGPT") }
)

@SecurityScheme(
        name = "BearerGPT",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        description = "Your ChatGPT API Key"
)
public class TestChatGPTapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestChatGPTapiApplication.class, args);
    }

}
