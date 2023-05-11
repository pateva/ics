package com.example.demo.controllers.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseRecord {
    private String _url;
    private List<LabelDto> _tags;
    private double _width;
    private double _height;

    public ResponseRecord() {
    }

    public ResponseRecord(String _url, List<LabelDto> _tags, double _width, double _height) {
        this._url = _url;
        this._tags = _tags;
        this._width = _width;
        this._height = _height;
    }

    public String get_url() {
        return _url;
    }

    public void set_url(String _url) {
        this._url = _url;
    }

    public List<LabelDto> get_tags() {
        return _tags;
    }

    public void set_tags(List<LabelDto> _tags) {
        this._tags = _tags;
    }

    public double get_width() {
        return _width;
    }

    public void set_width(double _width) {
        this._width = _width;
    }

    public double get_height() {
        return _height;
    }

    public void set_height(double _height) {
        this._height = _height;
    }
}
