package com.izicap.testchatgptapi.Models;

public class Response {
    private Answer answer;
    private Error error;

    private final int statusCode;

    public Response(Answer answer) {
        this.answer = answer;
        this.statusCode = 200;
    }

    public Response(Error error, int statusCode) {
        this.error = error;
        this.statusCode = statusCode;
    }

    public Answer getAnswer() {
        return answer;
    }

    public Error getError() {
        return error;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public boolean isValid() {
        return answer != null;
    }
}