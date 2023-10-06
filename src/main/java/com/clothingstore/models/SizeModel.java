package com.clothingstore.models;

public class SizeModel {
    private int id;
    private String size;

    public SizeModel() {
    }

    public SizeModel(int id, String size) {
        this.id = id;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public SizeModel id(int id) {
        setId(id);
        return this;
    }

    public SizeModel size(String size) {
        setSize(size);
        return this;
    }
}
