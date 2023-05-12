package com.example.demo.controllers.dto;

import com.example.demo.models.Image;
import com.example.demo.models.Label;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
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

    public static ResponseEntity<RecognitionResponseBody> imageToResponce(Image image, HttpStatus status) {
        List<LabelDto> labelDtos = new ArrayList<>();

        for (Label label : image.getLabels()) {
            labelDtos.add(new LabelDto(label.getLabelDescription()));
        }

        List<ResponseRecord> record = new ArrayList<>();
        ResponseRecord responseRecord = new ResponseRecord(image.getImageUrl(), labelDtos, image.getWidth(), image.getHeight());
        record.add(responseRecord);

        return new ResponseEntity<>(new RecognitionResponseBody(record), status);
    }

}

