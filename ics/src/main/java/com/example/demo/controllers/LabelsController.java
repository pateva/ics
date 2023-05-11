package com.example.demo.controllers;

import com.example.demo.controllers.dto.LabelDto;
import com.example.demo.models.Image;
import com.example.demo.models.Label;
import com.example.demo.repositories.LabelRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/labels")
public class LabelsController {
    @Autowired
    private LabelRepository labelRepository;

    @GetMapping
    public List<Label> listImages() {
        return labelRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Label getLabel(@PathVariable Long id) {
        return labelRepository.getReferenceById(id);
    }

    @PostMapping
    public Label createLabel(@RequestBody final Label label) {
        return labelRepository.saveAndFlush(label);
    }

    @RequestMapping(value = {"id"}, method = RequestMethod.DELETE)
    public void deleteLabel(@PathVariable Long id) {
        //todo children records before deleting
        labelRepository.deleteById(id);
    }

    @RequestMapping(value = {"id"}, method = RequestMethod.PUT)
    public Label updateLabel(@PathVariable Long id, @RequestBody Label label) {
        //todo add validation that all attributes are passed in, otherwise return 400 bad playload
        Label existingLabel = labelRepository.getReferenceById(id);
        BeanUtils.copyProperties(label, existingLabel, "label_id" );
        return labelRepository.saveAndFlush(existingLabel);

    }

    public  Label mapperLabel(LabelDto labelDto) {
        String labelDescription = labelDto.getName();

        if(labelRepository.existsByLabelDescription(labelDescription)) {
            return labelRepository.findByLabelDescription(labelDescription);
        }
        else {
            Label label = new Label(labelDescription);
            return labelRepository.saveAndFlush(label);
        }
    }
}
