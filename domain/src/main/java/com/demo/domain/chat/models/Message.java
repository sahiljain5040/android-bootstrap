package com.demo.domain.chat.models;

import com.google.gson.Gson;

public class Message {

    private String uuid;
    private String direction;
    private String message;
    private String timestamp;

    public Message( String uuid,  String direction,  String message,
                    String timestamp) {
        this.uuid = uuid;
        this.direction = direction;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid( String uuid) {
        this.uuid = uuid;
    }

    
    public String getDirection() {
        return direction;
    }

    public void setDirection( String direction) {
        this.direction = direction;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage( String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp( String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
