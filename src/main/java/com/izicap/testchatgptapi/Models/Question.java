package com.izicap.testchatgptapi.Models;

public class Question {
    private String model;
    private String prompt;
    private int max_tokens;
    private float temperature;

    public Question() { }

    public Question(String model, String prompt, int maxTokens, float temperature) {
        this.model = model;
        this.prompt = prompt;
        this.max_tokens = maxTokens;
        this.temperature = temperature;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public int getMax_tokens() {
        return max_tokens;
    }

    public void setMax_tokens(int max_tokens) {
        this.max_tokens = max_tokens;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }
}
