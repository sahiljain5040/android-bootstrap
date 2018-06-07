package com.demo.data.chat.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Message {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "uuid")
    private String uuid;

    @NonNull
    @ColumnInfo(name = "direction")
    private String direction;

    @NonNull
    @ColumnInfo(name = "message")
    private String message;

    @NonNull
    @ColumnInfo(name = "timestamp")
    private long timestamp;

    public Message(@NonNull String uuid, @NonNull String direction, @NonNull String message,
                   @NonNull long timestamp) {
        this.uuid = uuid;
        this.direction = direction;
        this.message = message;
        this.timestamp = timestamp;
    }

    @NonNull
    public String getUuid() {
        return uuid;
    }

    public void setUuid(@NonNull String uuid) {
        this.uuid = uuid;
    }

    @NonNull
    public String getDirection() {
        return direction;
    }

    public void setDirection(@NonNull String direction) {
        this.direction = direction;
    }

    @NonNull
    public String getMessage() {
        return message;
    }

    public void setMessage(@NonNull String message) {
        this.message = message;
    }

    @NonNull
    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(@NonNull long timestamp) {
        this.timestamp = timestamp;
    }
}
