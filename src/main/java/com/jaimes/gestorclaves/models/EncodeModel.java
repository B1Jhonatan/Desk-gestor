package com.jaimes.gestorclaves.models;

import javafx.collections.ObservableArray;

public class EncodeModel {

    private Integer id;
    private String name;
    private String encode;

    public EncodeModel() {
    }

    public EncodeModel(String encode, String name, Integer id) {
        this.encode = encode;
        this.name = name;
        this.id = id;
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
