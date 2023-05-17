package com.example.demo.repositories;

import com.example.demo.models.Label;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

class LabelRepositoryTest {

    @Mock
    private LabelRepository labelRepository;

    @Test
    public void givenLabelEntityRepository_whenSaveAndRetrieveEntity_thenOK() {
        Label label = labelRepository.save(new Label("test"));
        Label foundLabel = labelRepository.getReferenceById(label.getLabelId());

        assertNotNull(foundLabel);
        assertEquals(label.getLabelDescription(), foundLabel.getLabelDescription());
    }

}
