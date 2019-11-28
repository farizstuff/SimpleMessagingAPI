package com.fariz.rest.model;

public class OutputMessageModel {
    private String message;

    public OutputMessageModel(String message){
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
