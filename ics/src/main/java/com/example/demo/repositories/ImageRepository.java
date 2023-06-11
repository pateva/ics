package com.example.demo.repositories;

import com.example.demo.models.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ImageRepository extends PagingAndSortingRepository<Image, Long> {
    boolean existsByImageUrl(String imageUrl);
    Image findByImageUrl(String imageUrl);
    boolean existsById(Long id);
    Image findById(Long id);
    Image saveAndFlush(Image image);
    void deleteById(Long id);
    Image getReferenceById(Long id);


    @Query("SELECT i FROM Image i " +
            "JOIN i.labels l " +
            "WHERE l.labelDescription IN :labels")
    Page<Image> findImagesByLabels(@Param("labels") List<String> labels, Pageable pageable);
}
