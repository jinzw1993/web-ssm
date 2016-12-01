package com.heitian.ssm.bo;

/**
 * Created by Lanting on 2016/11/25.
 * status: 返回结果的状态 1-成功 0-失败
 * message: 失败和成功的信息
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
