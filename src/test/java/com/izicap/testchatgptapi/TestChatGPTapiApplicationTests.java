package com.izicap.testchatgptapi;

import com.izicap.testchatgptapi.DB.CSVWriter;
import com.izicap.testchatgptapi.Models.Question;
import com.izicap.testchatgptapi.Models.Response;
import com.izicap.testchatgptapi.Models.Error;
import com.izicap.testchatgptapi.Services.ChatGPTService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TestChatGPTapiApplicationTests {

    //We could also use the singleton, but for now are just testing
    @Autowired
    ChatGPTService chatGPTService;

    //Your ChatGPT API Key
    //The API Key should always be kept private. I exposed it only for the sake of this Coding Game
    final String apiKey = "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";

    @Test
    void testCSVWriter() throws IOException {
        CSVWriter.getInstance().put("Who is John Doe?",  "John Doe is a placeholder name used as a pseudonym in legal cases, when the true identity of the person is unknown or protected. It is also commonly used to refer to a hypothetical, average person.");
        CSVWriter.getInstance().close();

        //Get the last line of the file
        BufferedReader input = new BufferedReader(new FileReader("questions_answers.csv"));
        String last = null, line;
        while ((line = input.readLine()) != null) {
            last = line;
        }

        assertEquals("Who is John Doe?;John Doe is a placeholder name used as a pseudonym in legal cases, when the true identity of the person is unknown or protected. It is also commonly used to refer to a hypothetical, average person.", last);
    }

    @Test
    void testChatGPTService() {
        Response resp = chatGPTService.getChatGPTAnswer(
                Question.fromString("What is gluten sensitivity?"),
                apiKey
        );

        //We got our answer
        assertTrue(resp.isValid());

        // extract our answer
        String ans = resp.getAnswer().extractAnswer();

        System.out.println(ans);
    }

    @Test
    void testChatGPTServiceError() {
        Question question = Question.fromString("What is gluten sensitivity?");

        //this model obviously doesn't exist
        question.setModel("some-model");

        Response resp = chatGPTService.getChatGPTAnswer(
                question,
                apiKey
        );

        //We got our answer
        assertFalse(resp.isValid());

        // retrieve our error
        Error error = resp.getError();

        System.out.println(error);

        assertEquals("That model does not exist", error.getMessage());
    }
}
