package com.example.demo.controllers;


import com.example.demo.controllers.dto.RecognitionRequestBody;
import com.example.demo.controllers.dto.URL;
import com.example.demo.models.Image;
import com.example.demo.repositories.ImageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.servlet.function.RequestPredicates.param;


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

    @Test
    void getSingleImage_imageDoesExist_ReturnOk() throws Exception {
        Mockito.when(imageRepository.existsById(6L)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.get("/images/6")).andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void createImage_ImageDoesExist_returnOk() throws Exception {
        String url = "http://random.jpg";
        URL urlOb = new URL(url);
        List<URL> list = new ArrayList<>();
        list.add(urlOb);
        RecognitionRequestBody body = new RecognitionRequestBody(list);
        Mockito.when(imageRepository.existsByImageUrl(url)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/images")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))
                        .param("noCache", "false"))
                .andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    @Test
    void createImage_InvalidUrl_returnError() throws Exception {
        String url = "invalid_url";
        URL urlOb = new URL(url);
        List<URL> list = new ArrayList<>();
        list.add(urlOb);
        RecognitionRequestBody body = new RecognitionRequestBody(list);
        Mockito.when(imageRepository.existsByImageUrl(url)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.post("/images")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))
                        .param("noCache", "false"))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }

    @Test
    void deleteImage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/images/6")).andExpect(MockMvcResultMatchers.status().isOk());
    }
}

