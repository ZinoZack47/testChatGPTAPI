package com.izicap.testchatgptapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
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
public class TestChatGPTapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestChatGPTapiApplication.class, args);
    }

}
