package com.example.demo.controllers;

import com.example.demo.controllers.dto.LabelDto;
import com.example.demo.controllers.dto.RecognitionRequestBody;
import com.example.demo.controllers.dto.RecognitionResponseBody;
import com.example.demo.controllers.dto.ResponseRecord;
import com.example.demo.models.Image;
import com.example.demo.models.Label;
import com.example.demo.repositories.ImageRepository;
import com.example.demo.services.XimilarAPI;
import com.google.common.util.concurrent.RateLimiter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/images")
public class ImagesController {
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private LabelsController labelsController;

    @GetMapping
    public List<Image> listImages(@RequestParam(required = false) List<String> labels) {
        if (labels == null) {
            return imageRepository.findAll();
        } else {
            return imageRepository.findImagesByLabels(labels);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Image> getImage(@PathVariable Long id) {
        if (!imageRepository.existsById(id)) {

            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(imageRepository.getReferenceById(id), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Image> classifyImage(@RequestBody RecognitionRequestBody body,
                                               @RequestParam(required = false, defaultValue = "false") boolean noCache) {
        RateLimiter rateLimiter = RateLimiter.create(10);
        ResponseEntity<RecognitionResponseBody> responseEntity;

        if (!rateLimiter.tryAcquire()) {

            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }

        if (!body.isValidUrl()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }

        String url = body.getRecords().get(0).get_url();

        if (imageRepository.existsByImageUrl(url) && !noCache) {
            Image image = imageRepository.findByImageUrl(url);

            return new ResponseEntity<>(image, HttpStatus.ACCEPTED);

        } else {
            responseEntity = XimilarAPI.postApiTagRequestXimilar(body);
            Image image = mapperImage(responseEntity.getBody().getRecords().get(0));
            if (imageRepository.existsById(123L)) {
                //todo nice to have by logic da poznae image-a, nqma da e ID
            }

            imageRepository.saveAndFlush(image);

            return new ResponseEntity<>(image, HttpStatus.OK);
        }
    }


    @DeleteMapping(value = {"/{id}"})
    public void deleteImage(@PathVariable Long id) {
        imageRepository.deleteById(id);
    }

    @PutMapping(value = {"/{id}"})
    public ResponseEntity<Image> updateImage(@PathVariable Long id, @RequestBody String url) {
        Image existingImage = imageRepository.getReferenceById(id);
        BeanUtils.copyProperties(imageRepository.findByImageUrl(url), existingImage, "image_id");
        existingImage = imageRepository.saveAndFlush(existingImage);

        return new ResponseEntity<>(existingImage, HttpStatus.OK);
    }

    private Image mapperImage(ResponseRecord response) {
        Set<Label> labelSet = new HashSet<>();

        for (LabelDto label : response.get_tags()) {
            labelSet.add(labelsController.mapperLabel(label));
        }

        return new Image(response.get_url(), response.get_width(), response.get_height(), labelSet);
    }

}
