package com.example.demo.controllers.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecognitionResponseBody {
    private List<ResponseRecord> records;

    public RecognitionResponseBody() {
    }

    public RecognitionResponseBody(List<ResponseRecord> records) {
        this.records = records;
    }

    public List<ResponseRecord> getRecords() {
        return records;
    }

    public void setRecords(List<ResponseRecord> records) {
        this.records = records;
    }
}
