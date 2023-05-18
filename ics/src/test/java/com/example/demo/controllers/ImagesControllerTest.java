package com.example.demo.controllers;


import com.example.demo.controllers.dto.LabelDto;
import com.example.demo.models.Image;
import com.example.demo.models.Label;
import com.example.demo.repositories.ImageRepository;
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

@WebMvcTest(ImagesController.class)
@ExtendWith(SpringExtension.class)
class ImagesControllerTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private ImageRepository imageRepository;

    @MockBean
    private LabelsController labelsController;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void listImages() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/images")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getSingleImage_imageDoesNotExist_ReturnNotFound() throws Exception {
        Mockito.when(imageRepository.existsById(6L)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.get("/images/6")).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

//    @Test
//    void getSingleImage_imageDoesExist_ReturnOk() throws Exception {
//        Mockito.when(imageRepository.existsById(6L)).thenReturn(true);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/images/6")).andExpect(MockMvcResultMatchers.status().isNotFound());
//    }

//    @Test
//    void createImage_ImageDoesNotExist_returnNewImage() throws Exception {
//        Mockito.when(imageRepository.existsByImageUrl("random")).thenReturn(false);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/images")
//                        .content(objectMapper.writeValueAsString(new LabelDto("random")))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//
//        Mockito.verify(imageRepository).saveAndFlush(new Image());
//    }
//
//    @Test
//    void deleteImage() {
//    }
//
//    @Test
//    void updateImage() {
//    }
}
