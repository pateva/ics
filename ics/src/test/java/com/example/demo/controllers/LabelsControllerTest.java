package com.example.demo.controllers;

import com.example.demo.controllers.dto.LabelDto;
import com.example.demo.models.Label;
import com.example.demo.repositories.LabelRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.example.demo.controllers.dto.LabelDto.asJsonString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;


@WebMvcTest(LabelsController.class)
@ExtendWith(SpringExtension.class)
class LabelsControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private LabelsController controller;

    @MockBean
    private LabelRepository labelRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void listLabel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/labels")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void listSingleLabel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/labels/1")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteLabel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/labels/1")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void createLabel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/labels")
                        .content(objectMapper.writeValueAsString(new LabelDto("vector")))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.when(labelRepository.existsByLabelDescription("vector")).thenReturn(false);
        Mockito.when(labelRepository.saveAndFlush(any(Label.class))).thenReturn(new Label("vector"));

    }

    @Test
    void updateLabel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/labels/1")
                        .content(objectMapper.writeValueAsString(new LabelDto("vector")))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.when(labelRepository.existsByLabelDescription("vector")).thenReturn(false);
        Mockito.when(labelRepository.saveAndFlush(any(Label.class))).thenReturn(new Label("vector"));

    }


}
