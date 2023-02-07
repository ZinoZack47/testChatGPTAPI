package com.izicap.testchatgptapi.Models;

public class Usage {
    private int prompt_tokens;
    private int completion_tokens;
    private int total_tokens;

    public Usage() { }

    public Usage(int promptTokens, int completionTokens, int totalTokens) {
        this.prompt_tokens = promptTokens;
        this.completion_tokens = completionTokens;
        this.total_tokens = totalTokens;
    }

    public int getPrompt_tokens() {
        return prompt_tokens;
    }

    public void setPrompt_tokens(int prompt_tokens) {
        this.prompt_tokens = prompt_tokens;
    }

    public int getCompletion_tokens() {
        return completion_tokens;
    }

    public void setCompletion_tokens(int completion_tokens) {
        this.completion_tokens = completion_tokens;
    }

    public int getTotal_tokens() {
        return total_tokens;
    }

    public void setTotal_tokens(int total_tokens) {
        this.total_tokens = total_tokens;
    }
}
