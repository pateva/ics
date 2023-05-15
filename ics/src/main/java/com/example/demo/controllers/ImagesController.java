package com.example.demo.controllers;

import com.example.demo.controllers.dto.LabelDto;
import com.example.demo.controllers.dto.RecognitionRequestBody;
import com.example.demo.controllers.dto.RecognitionResponseBody;
import com.example.demo.controllers.dto.ResponseRecord;
import com.example.demo.models.Image;
import com.example.demo.models.Label;
import com.example.demo.repositories.ImageRepository;
import com.example.demo.services.XimilarAPI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/images")
public class ImagesController {
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private LabelsController labelsController;

    @GetMapping
    public List<Image> listImages() {
        return imageRepository.findAll();
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<RecognitionResponseBody> getImage(@PathVariable Long id) {
        if (!imageRepository.existsById(id)) {

            return ResponseEntity.notFound().build();
        }

        return RecognitionResponseBody.imageToResponce(
                imageRepository.getReferenceById(id)
                , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RecognitionResponseBody> classifyImage(@RequestBody RecognitionRequestBody body) {
        if (!body.isValidUrl()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        String url = body.getRecords().get(0).get_url();
        ResponseEntity<RecognitionResponseBody> responseEntity;

        if (imageRepository.existsByImageUrl(url)) {
            responseEntity = RecognitionResponseBody.imageToResponce(imageRepository.findByImageUrl(url), HttpStatus.ACCEPTED);

        } else {
            responseEntity = XimilarAPI.postApiTagRequestXimilar(body);
            Image image = mapperImage(responseEntity.getBody().getRecords().get(0));

            if (imageRepository.existsById(123L)) {
                //todo nice to have by logic da poznae image-a, nqma da e ID
            }

            imageRepository.saveAndFlush(image);
        }

        return responseEntity;
    }


    @RequestMapping(value = {"/id"}, method = RequestMethod.DELETE)
    public void deleteImage(@PathVariable Long id) {
        imageRepository.deleteById(id);
    }

    @Validated
    @RequestMapping(value = {"/id"}, method = RequestMethod.PUT)
    public Image updateImage(@PathVariable Long id, @RequestBody Image image) {
        Image existingImage = imageRepository.getReferenceById(id);
        BeanUtils.copyProperties(image, existingImage, "image_id");
        return imageRepository.saveAndFlush(existingImage);

    }

    private Image mapperImage(ResponseRecord response) {
        Set<Label> labelSet = new HashSet<>();

        for (LabelDto label : response.get_tags()) {
            labelSet.add(labelsController.mapperLabel(label));
        }

        return new Image(response.get_url(), response.get_width(), response.get_height(), labelSet);
    }
}
