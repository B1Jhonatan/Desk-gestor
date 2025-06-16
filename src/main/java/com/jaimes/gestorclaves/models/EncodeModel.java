package com.jaimes.gestorclaves.models;

import javafx.collections.ObservableArray;

public class EncodeModel {

    private Integer id;
    private String name;
    private String encode;
    private Integer userId;

    public EncodeModel() {
    }

    public EncodeModel(Integer id, String name, String encode, Integer userId) {
        this.id = id;
        this.name = name;
        this.encode = encode;
        this.userId = userId;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
