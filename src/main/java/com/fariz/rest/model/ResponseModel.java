package com.fariz.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseModel {
    @JsonProperty
    private int code;

    @JsonProperty
    private String messageStatus;

    @JsonProperty
    private Object content;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}

