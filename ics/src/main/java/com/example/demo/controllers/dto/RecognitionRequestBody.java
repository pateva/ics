package com.example.demo.controllers.dto;

import com.example.demo.exceptions.InvalidUrlException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

public class RecognitionRequestBody {
    private List<URL> records;
    private UUID task_id;

    public RecognitionRequestBody(List<URL> records) {
        this.records = records;
        this.task_id = UUID.randomUUID();
    }

    public RecognitionRequestBody(List<URL> records, UUID task) {
        this.records = records;
        this.task_id = task;
    }

    public RecognitionRequestBody() {
    }

    public List<URL> getRecords() {
        return records;
    }

    public void setRecords(List<URL> records) {
        this.records = records;
    }

    public UUID getTask_id() {
        return task_id;
    }

    public void setTask_id(UUID task_id) {
        this.task_id = task_id;
    }

    public boolean isValidUrl() {
        if (records.size() != 1) throw new InvalidUrlException();

        try {
            URI uri = new URI(records.get(0).get_url());
            return uri.isAbsolute() && uri.getScheme().matches("^https?$");
        } catch (URISyntaxException e) {
            return false;
        }
    }
}

