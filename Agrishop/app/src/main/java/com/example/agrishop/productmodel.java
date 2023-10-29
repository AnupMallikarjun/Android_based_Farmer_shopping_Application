package com.example.agrishop;

import java.io.Serializable;

public class productmodel implements Serializable {
    String description;
    String  name;
    String offer;
    int rate;
    String type;
    String uri;

    public productmodel() {
    }

    public productmodel(String description, String name, String offer, int rate, String type, String uri) {
        this.description = description;
        this.name = name;
        this.offer = offer;
        this.rate = rate;
        this.type = type;
        this.uri = uri;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
