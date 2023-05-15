package com.example.demo.services;

import com.example.demo.controllers.dto.LabelDto;
import com.example.demo.controllers.dto.RecognitionResponseBody;
import com.example.demo.controllers.dto.ResponseRecord;
import com.example.demo.models.Image;
import com.example.demo.models.Label;
import com.example.demo.repositories.ImageRepository;
import com.example.demo.repositories.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Set;

public class ImageClassificationService {
//    private Image mapperImage(ResponseRecord response) {
//        Set<Label> labelSet = new HashSet<>();
//
//        for (LabelDto label : response.get_tags()) {
//            labelSet.add(labelsController.mapperLabel(label));
//            //todo this could be in a service
//        }
//
//        return new Image(response.get_url(), response.get_width(), response.get_height(), labelSet);
//    }


}


