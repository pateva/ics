package com.example.demo.controllers;

import com.example.demo.models.Image;
import com.example.demo.repositories.ImageRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/images")
public class ImagesController {

    @Autowired
    private ImageRepository imageRepository;

    @GetMapping
    public List<Image> listImages() {
        return imageRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Image getImage(@PathVariable Long id) {
        return imageRepository.getReferenceById(id);
    }

    @PostMapping
    public Image createImage(@RequestBody final Image image) {
        return imageRepository.saveAndFlush(image);
    }

    @RequestMapping(value = {"id"}, method = RequestMethod.DELETE)
    public void deleteImage(@PathVariable Long id) {
        //todo children records before deleting
        imageRepository.deleteById(id);
    }

    @RequestMapping(value = {"id"}, method = RequestMethod.PUT)
    public Image updateImage(@PathVariable Long id, @RequestBody Image image) {
        //todo add validation that all attributes are passed in, otherwise return 400 bad playload
        Image existingImage = imageRepository.getReferenceById(id);
        BeanUtils.copyProperties(image, existingImage, "image_id" );
        return imageRepository.saveAndFlush(existingImage);

    }


}
