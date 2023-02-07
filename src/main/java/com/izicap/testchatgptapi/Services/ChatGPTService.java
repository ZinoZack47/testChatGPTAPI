package com.izicap.testchatgptapi.Services;

import com.izicap.testchatgptapi.Models.Answer;
import com.izicap.testchatgptapi.Models.Question;
import com.izicap.testchatgptapi.Models.Response;

import com.izicap.testchatgptapi.Models.Records.ErrorRecord;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class ChatGPTService implements IChatGPTService {
    private final RestTemplate restTemplate;

    public ChatGPTService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Response getChatGPTAnswer(Question question, String APIKey) {
        try {
            //Url
            final var url = "https://api.openai.com/v1/completions";

            //Creating Chat GPT Request
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(APIKey);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            HttpEntity<Question> request = new HttpEntity<>(question, headers);

            //Call ChatGPT
            ResponseEntity<Answer> responseEntity = this.restTemplate.exchange(url, HttpMethod.POST, request, Answer.class);

            return new Response(responseEntity.getBody());

        } catch (HttpStatusCodeException ex) {
            // get error body e.g. `429 type: insufficient_quota
            ErrorRecord errW = ex.getResponseBodyAs(ErrorRecord.class);
            assert errW != null;
            return new Response(errW.error(), ex.getStatusCode().value());
        }
    }

    // `Field Injection is not recommended`
    private static ChatGPTService instance = null;
    public static ChatGPTService getInstance() {
        if (instance == null) {
            instance = new ChatGPTService(new RestTemplateBuilder());
        }
        return instance;
    }
}
