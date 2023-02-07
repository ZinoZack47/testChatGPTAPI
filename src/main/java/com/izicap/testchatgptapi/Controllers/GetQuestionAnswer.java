package com.izicap.testchatgptapi.Controllers;

import com.izicap.testchatgptapi.DB.CSVWriter;
import com.izicap.testchatgptapi.DB.IDBWriter;
import com.izicap.testchatgptapi.Models.Question;
import com.izicap.testchatgptapi.Models.Response;

import com.izicap.testchatgptapi.Services.ChatGPTService;
import com.izicap.testchatgptapi.Services.IChatGPTService;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GetQuestionAnswer {
    IChatGPTService chatGPTService = ChatGPTService.getInstance();
    IDBWriter dbWriter = CSVWriter.getInstance();
    @GetMapping("/ask/{qst}&{api}")
    @Operation(
            tags = {"AskQuestion"},
            operationId = "askQuestionQuick",
            summary = "Ask a question directly",
            description = """
                    This functionality uses the model 'text-davinci-003' with max_tokens set to 4000 and temperature set to 1.0.
            """,
            parameters = {
                    @Parameter(
                        name = "qst",
                        description = "question/sentence to send to ChatGPT",
                        example = "What is gluten sensitivity?",
                        in = ParameterIn.PATH
                    ),
                    @Parameter(
                        schema = @Schema(name = "apikey", format = "password"),
                        name = "api",
                        description = "your Chat GPT API Key",
                        example = "sk-....47zi",
                        in = ParameterIn.PATH
                    )
            },
            responses = {
                    @ApiResponse(
                        responseCode = "200",
                        content = @Content(schema = @Schema(implementation = Question.class), mediaType = MediaType.APPLICATION_JSON_VALUE),
                        description = "Answer: "
            )}
            ,security = {@SecurityRequirement(name = "bearerGPT")}
    )
    public ResponseEntity<Object> getQuickQuestionAnswerEP(
            @PathVariable String qst,
            @PathVariable String api
    ) {
        Question question = Question.fromString(qst);
        Response resp = chatGPTService.getChatGPTAnswer(question, api);

        if(!resp.isValid())
            return ResponseEntity.status(resp.getStatusCode()).body(resp.getError());

        //Extract answer
        String ans = resp.getAnswer().extractAnswer();

        //Store our answer in our db (CSV File in this case)
        dbWriter.put(qst, ans);

        return ResponseEntity.ok().body(ans);
    }

    @PostMapping(value = "/question/{api}")
    @Operation(
            tags = {"AskQuestion"},
            operationId = "askQuestionFull",
            summary = "Ask a question specifying all available inputs",
            description = """
                    This functionality requires you to specify all required inputs which are:
                    model: which model to use. for example: 'text-davinci-003'.
                    prompt: your question/sentence.
                    max_tokens: The maximum number of tokens to generate in the completion (1000 tokens are roughly 750 words).
                    temperature: What sampling temperature to use. Higher values means the model will take more risks.
            """,
            parameters = {
                    @Parameter(
                        schema = @Schema(name = "api", format = "password"),
                        name = "api",
                        description = "Your Chat GPT API Key",
                        in = ParameterIn.PATH
            )},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content =  @Content(schema = @Schema(implementation = Question.class))),
            responses = {
                    @ApiResponse(
                        responseCode = "200",
                        content = @Content(schema = @Schema(implementation = Question.class), mediaType = MediaType.APPLICATION_JSON_VALUE),
                        description = "Answer: "
                    )
            },
            externalDocs = @ExternalDocumentation(
                    url = "https://platform.openai.com/docs/",
                    description = "Check out OpenAI Docs for more information."
            )
            ,security = {@SecurityRequirement(name = "bearerGPT")}
    )
    public ResponseEntity<Object> getFullQuestionAnswerEP(
            @PathVariable String api,
            @RequestBody Question question
    ) {
        Response resp = chatGPTService.getChatGPTAnswer(question, api);

        if (resp.isValid()) {
            //Extract answer
            String ans = resp.getAnswer().extractAnswer();

            //Store our answer in our db (CSV File in this case)
            dbWriter.put(question.getPrompt(), ans);

           return ResponseEntity.ok().body(resp.getAnswer());
        }

        return ResponseEntity.status(resp.getStatusCode()).body(resp.getError());
    }
}
