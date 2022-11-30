package com.example.phonems.exceptions;

public class ErrorResponse {

    private String msg;
    private int responseCode;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    @Override
    public String toString() {
        return "Error: "+ responseCode +"\nMessage: "+msg;
    }
}
