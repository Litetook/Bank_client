package com.pragmatic.controller.exception;

import java.time.Instant;
import java.util.Date;

public class ErrorMessage {
    String status;
    Instant time = Instant.now();
    String message;
    String description;

    ErrorMessage() {};

    ErrorMessage(String status, String message, String description) {
        this.status = status;
        this.message = message;
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "status='" + status + '\'' +
                ", time=" + time +
                ", message='" + message + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
