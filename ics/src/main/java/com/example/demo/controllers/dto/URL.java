package com.example.demo.controllers.dto;

public class URL {
    private String _url;

    public URL() {
    }

    public URL(String url) {
        this._url = url;
    }

    public String get_url() {
        return _url;
    }

    public void set_url(String _url) {
        this._url = _url;
    }

    @Override
    public String toString() {
        return "URL{" +
                "_url='" + _url + '\'' +
                '}';
    }
}
