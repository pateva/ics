package com.example.demo.repositories;

public abstract class BaseRepository {
    private final String API_URL;
    private final String API_KEY;

    protected BaseRepository() {
        API_URL = "https://api.ximilar.com/classify/v2/classify";
        API_KEY = System.getenv("API_KEY_XIMILAR");
    }
}
