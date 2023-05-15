//package com.example.demo.controllers;
//
//import com.example.demo.controllers.dto.LabelDto;
//import com.example.demo.models.Label;
//import com.example.demo.repositories.LabelRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//@ActiveProfiles("test")
//@WebMvcTest
//class LabelsControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private LabelsController controller;
//
//    @Test
//    public void contextLoads() throws Exception {
//        assertThat(controller).isNotNull();
//    }
//
//    @Test
//    public void createLabel_WithNewLabel_ShouldReturnCreatedLabel() throws Exception {
////// Arrange
////        LabelDto labelDto = new LabelDto();
////        labelDto.setName("New Label");
////
////        // Act & Assert
////        mockMvc.perform(MockMvcRequestBuilders.post("/labels")
////                        .contentType(MediaType.APPLICATION_JSON)
////                        .content(objectMapper.writeValueAsString(labelDto)))
////                .andExpect(MockMvcResultMatchers.status().isOk())
////                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(labelDto.getName()));
//    }
//
//    @Test
//    public void createLabel_WithExistingLabel_ShouldReturnExistingLabel() throws Exception {
//
//    }
//}
