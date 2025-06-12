package com.jaimes.gestorclaves.models;

public class EncodeModel {

    private String name;
    private String encode;

    public EncodeModel() {
    }

    public EncodeModel(String name, String encode) {
        this.name = name;
        this.encode = encode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEncode() {
        return encode;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }
}
