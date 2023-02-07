package com.izicap.testchatgptapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.izicap.testchatgptapi.Models.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class TestGetQuestionAnswer {

    //Your ChatGPT API Key
    //The API Key should always be kept private. I exposed it only for the sake of this Coding Game
    final String apiKey = "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";

    @Autowired
    private WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    @BeforeEach
    public void setup()
    {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testQuestionQuick() throws Exception {
        String qst = "What is jenkins?";
        mockMvc.perform(get("/ask/{qst}&{apiKey}", qst, apiKey))
                .andExpect(status().isOk());}

    @Test
    void testQuestionFull() throws Exception {
        Question question = Question.fromString("What is jenkins?");

        mockMvc.perform(post("/question/" + apiKey)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(question)))
                .andExpect(status().isOk());
    }
}
