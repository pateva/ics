package com.example.demo.repositories;

import com.example.demo.models.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long> {
    boolean existsByLabelDescription(String labelDescription);
    Label findByLabelDescription(String labelDescription);
    Label findByLabelId(Long labelId);
}

