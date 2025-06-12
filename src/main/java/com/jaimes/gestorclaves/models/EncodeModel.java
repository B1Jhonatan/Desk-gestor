package com.jaimes.gestorclaves.models;

public class EncodeModel {

    private Integer id;
    private String name;
    private String encode;

    public EncodeModel() {
    }

    public EncodeModel(Integer id, String name, String encode) {
        this.id = id;
        this.name = name;
        this.encode = encode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
