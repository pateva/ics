package com.example.demo.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class URL {
    @JsonProperty("_url")
    private String url;

    public URL() {
    }

    public URL(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "URL{" +
                "url='" + url + '\'' +
                '}';
    }
}
