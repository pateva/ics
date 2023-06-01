package com.example.demo.controllers;

import com.example.demo.controllers.dto.LabelDto;
import com.example.demo.controllers.dto.RecognitionRequestBody;
import com.example.demo.controllers.dto.RecognitionResponseBody;
import com.example.demo.controllers.dto.ResponseRecord;
import com.example.demo.exceptions.InvalidUrlException;
import com.example.demo.exceptions.ResourceNotFoundException;
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
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/images")
public class ImagesController {

    private final ImageRepository imageRepository;
    private final LabelsController labelsController;

    @Autowired
    public ImagesController(ImageRepository imageRepository, LabelsController labelsController) {
        this.imageRepository = imageRepository;
        this.labelsController = labelsController;
    }

    @GetMapping
    public ResponseEntity<List<Image>> listImages(@RequestParam(required = false) List<String> labels) {
        if (labels == null) {
            return new ResponseEntity<>(imageRepository.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(imageRepository.findImagesByLabels(labels), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Image> getImage(@PathVariable Long id) {
        if (!imageRepository.existsById(id)) {

            throw new ResourceNotFoundException("Image id does not exist!");
        }

        return new ResponseEntity<>(imageRepository.findById(id).get(), HttpStatus.OK);
    }

    
    @PostMapping
    public ResponseEntity<Image> classifyImage(@RequestBody RecognitionRequestBody body,
                                               @RequestParam(required = false, defaultValue = "false") boolean noCache) {
        RateLimiter rateLimiter = RateLimiter.create(10);
        RecognitionResponseBody responseEntity;

        if (!rateLimiter.tryAcquire()) {

            throw new RuntimeException("Too many requests!");
        }

        if (!body.isValidUrl()) {

            throw new InvalidUrlException();
        }

        String url = body.getRecords().get(0).getUrl();


        if (imageRepository.existsByImageUrl(url) && !noCache) {
            Image image =  imageRepository.findByImageUrl(url);

            return new ResponseEntity<>(image, HttpStatus.ACCEPTED);
        } else {
            responseEntity = XimilarAPI.postApiTagRequestXimilar(body);
            Image image = mapperImage(responseEntity.getRecords().get(0));
            if (imageRepository.existsById(123L)) {
                //todo nice to have by logic da poznae image-a, nqma da e ID
            }

            imageRepository.saveAndFlush(image);
            return new ResponseEntity<>(image, HttpStatus.OK);
        }
    }


    @DeleteMapping(value = {"/{id}"})
    public void deleteImage(@PathVariable Long id) {

        if(!imageRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found");
        }

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

        for (LabelDto label : response.getTags()) {
            labelSet.add(labelsController.mapperLabel(label));
        }

        return new Image(response.getUrl(), response.getWidth(), response.getHeight(), labelSet);
    }

}
