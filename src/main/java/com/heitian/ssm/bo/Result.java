package com.heitian.ssm.bo;

/**
 * Created by Lanting on 2016/11/25.
 */
public class Result {
    private int status;
    private String message;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(int status) {

        this.status = status;
    }
}