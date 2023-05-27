package com.example.demo.controllers.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseRecord {
    @JsonProperty("_url")
    private String url;

    @JsonProperty("_tags")
    private List<LabelDto> tags;

    @JsonProperty("_width")
    private double width;

    @JsonProperty("_height")
    private double height;

    public ResponseRecord() {
    }

    public ResponseRecord(String url, List<LabelDto> tags, double width, double height) {
        this.url = url;
        this.tags = tags;
        this.width = width;
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<LabelDto> getTags() {
        return tags;
    }

    public void setTags(List<LabelDto> tags) {
        this.tags = tags;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
