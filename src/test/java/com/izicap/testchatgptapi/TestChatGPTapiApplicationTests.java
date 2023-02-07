package com.izicap.testchatgptapi;

import com.izicap.testchatgptapi.DB.CSVWriter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class TestChatGPTapiApplicationTests {

    @Test
    void testCSVWriter() throws IOException {
        CSVWriter.getInstance().put("Who is John Doe?",  "John Doe is a placeholder name used as a pseudonym in legal cases, when the true identity of the person is unknown or protected. It is also commonly used to refer to a hypothetical, average person.");
        CSVWriter.getInstance().close();

        BufferedReader input = new BufferedReader(new FileReader("questions_answers.csv"));
        String last = null, line;

        while ((line = input.readLine()) != null) {
            last = line;
        }

        assertEquals(last, "Who is John Doe?;John Doe is a placeholder name used as a pseudonym in legal cases, when the true identity of the person is unknown or protected. It is also commonly used to refer to a hypothetical, average person.");
    }
}
