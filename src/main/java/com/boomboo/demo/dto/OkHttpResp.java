package com.boomboo.demo.dto;

import java.io.Serializable;

public class OkHttpResp implements Serializable {

    private int code;
    private String header;
    private String body;
    private String message;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccessful() {
        return this.code >= 200 && this.code < 300;
    }

    @Override
    public String toString() {
        return "OkHttpResp{" +
                "code=" + code +
                ", header='" + header + '\'' +
                ", body='" + body + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
