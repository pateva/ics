package com.example.demo.repositories;

import com.example.demo.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    boolean existsByImageUrl(String imageUrl);
    Image findByImageUrl(String imageUrl);

    @Query("SELECT i FROM Image i " +
            "JOIN i.labels l " +
            "WHERE l.labelDescription IN :labels")
    List<Image> findImagesByLabels(@Param("labels") List<String> labels);
}
