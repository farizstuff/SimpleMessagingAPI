package com.fariz.rest.model;

public class InputMessageModel {
    private String message;

    public InputMessageModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
