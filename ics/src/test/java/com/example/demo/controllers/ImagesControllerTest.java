package com.example.demo.controllers;


import com.example.demo.repositories.ImageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;;
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

    @MockBean
    private ImagesController imagesController;

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


    //    @Test
//    void createImage_ImageDoesNotExist_returnNewImage() throws Exception {
//        Mockito.when(imageRepository.existsByImageUrl("random")).thenReturn(false);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/images")
//                        .content(objectMapper.writeValueAsString(new LabelDto("random")))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//
//        Mockito.verify(imageRepository).saveAndFlush(new Image());
    //  }
//
    @Test
    void deleteImage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/images/6")).andExpect(MockMvcResultMatchers.status().isOk());
    }
}

//    @Test
//    void updateImage() throws Exception {
//        // Arrange
//        Long id = 1L;
//        String url = "http://example.com/image.jpg";
//        Image existingImage = new Image();
//        existingImage.setImageId(id);
//        existingImage.setImageUrl(url);
//
//        Image updatedImage = new Image();
//        updatedImage.setImageId(id);
//        updatedImage.setImageUrl(url + "_updated");
//
//        Mockito.when(imageRepository.getReferenceById(id)).thenReturn(existingImage);
//        Mockito.when(imageRepository.findByImageUrl(url)).thenReturn(updatedImage);
//        Mockito.when(imageRepository.saveAndFlush(any(Image.class))).thenReturn(updatedImage);
//
//        // Act
//        ResponseEntity<Image> response = imagesController.updateImage(id, url);
//
//        // Ass
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//    }


