package com.example.demo.controllers;

import com.example.demo.controllers.dto.LabelDto;
import com.example.demo.models.Label;
import com.example.demo.repositories.LabelRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest(LabelsController.class)
@ExtendWith(SpringExtension.class)
class LabelsControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

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
    void createLabel_labelDoesNotExist_returnNewLabel() throws Exception {
        Mockito.when(labelRepository.existsByLabelDescription("vector")).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.post("/labels")
                        .content(objectMapper.writeValueAsString(new LabelDto("vector")))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(labelRepository).saveAndFlush(new Label("vector"));
    }

    @Test
    void createLabel_labelAlreadyExist_returnExistingLabel() throws Exception {
        Mockito.when(labelRepository.existsByLabelDescription("vector")).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post("/labels")
                        .content(objectMapper.writeValueAsString(new LabelDto("vector")))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(labelRepository).findByLabelDescription("vector");
    }

}
