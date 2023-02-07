package com.izicap.testchatgptapi.Models;

public class Choice {
    private String text;
    private int index;
    private Float logprobs;
    private String finish_reason;

    public Choice() { }

    public Choice(String text, int index, Float logprobs, String finish_reason) {
        this.text = text;
        this.index = index;
        this.logprobs = logprobs;
        this.finish_reason = finish_reason;
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

    public String getFinish_reason() {
        return finish_reason;
    }

    public void setFinish_reason(String finish_reason) {
        this.finish_reason = finish_reason;
    }
}
