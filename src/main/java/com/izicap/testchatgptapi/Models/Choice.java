package com.izicap.testchatgptapi.Models;

public class Choice {
    private String text;
    private int index;
    private Float logprobs;

    public Choice() { }

    public Choice(String text, int index, Float logprobs) {
        this.text = text;
        this.index = index;
        this.logprobs = logprobs;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Float getLogprobs() {
        return logprobs;
    }

    public void setLogprobs(Float logprobs) {
        this.logprobs = logprobs;
    }
}
