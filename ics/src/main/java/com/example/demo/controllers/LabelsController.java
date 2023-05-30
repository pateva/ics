package com.example.demo.controllers;

import com.example.demo.controllers.dto.LabelDto;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.models.Label;
import com.example.demo.repositories.LabelRepository;
import org.json.HTTP;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/labels")
public class LabelsController {
    private LabelRepository labelRepository;

    @Autowired
    public LabelsController(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    @GetMapping
    public ResponseEntity<List<Label>> listLabels() {
        return new ResponseEntity<>(labelRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Label> getLabel(@PathVariable Long id) {
        if (!labelRepository.existsById(id)) {
            throw new ResourceNotFoundException("Label id does not exist");
        }

        return new ResponseEntity<>(labelRepository.findById(id).get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Label> createLabel(@RequestBody final LabelDto labelDto) {
        String labelDescription = labelDto.getName();

        if (labelRepository.existsByLabelDescription(labelDescription)) {

            return new ResponseEntity<>(labelRepository.findByLabelDescription(labelDescription), HttpStatus.OK);
        } else {

            return  new ResponseEntity<>(labelRepository.saveAndFlush(new Label(labelDescription)), HttpStatus.OK);
        }
    }

    @DeleteMapping(value = {"/{id}"})
    public void deleteLabel(@PathVariable Long id) {
        labelRepository.deleteById(id);
    }

    @PutMapping(value = {"/{id}"})
    public ResponseEntity<Label> updateLabel(@PathVariable Long id, @RequestBody Label label) {
        Label existingLabel = labelRepository.getReferenceById(id);
        BeanUtils.copyProperties(label, existingLabel, "label_id");
        return new ResponseEntity<>(labelRepository.saveAndFlush(existingLabel), HttpStatus.OK);

    }

    public Label mapperLabel(LabelDto labelDto) {
        String labelDescription = labelDto.getName();

        if (labelRepository.existsByLabelDescription(labelDescription)) {
            return labelRepository.findByLabelDescription(labelDescription);
        } else {
            Label label = new Label(labelDescription);

            return labelRepository.saveAndFlush(label);
        }
    }
}
