package com.izicap.testchatgptapi;

import com.izicap.testchatgptapi.DB.CSVWriter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestChatGPTapiApplicationTests {

    @Test
    void testCSVWriter() {
        CSVWriter.getInstance().put("Who is John Doe?",  "John Doe is a placeholder name used as a pseudonym in legal cases, when the true identity of the person is unknown or protected. It is also commonly used to refer to a hypothetical, average person.");
    }
}
