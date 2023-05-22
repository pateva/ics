package com.example.demo.models;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "images")
public class Image {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @Column(name = "\"image_name\"")
    private String imageUrl;

    @CreationTimestamp
    @Column(name = "\"created_at\"")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "\"updated_at\"")
    private LocalDateTime updatedAt;

    @Column(name = "\"image_width\"")
    private double width;

    @Column(name = "\"image_height\"")
    private double height;

    @Column(name = "\"image_service\"")
    private String imageService = "Ximilar";

    @ManyToMany
    @JoinTable(
            name = "\"image_label\"",
            joinColumns = @JoinColumn(name = "\"image_id\""),
            inverseJoinColumns = @JoinColumn(name = "\"label_id\""))
    Set<Label> labels;

    public Image() {}

    public Image(String imageUrl, double width, double height, Set<Label> labels) {
        this.imageUrl = imageUrl;
        this.width = width;
        this.height = height;
        this.labels = labels;
    }

    public Image(Long imageId, String imageUrl, LocalDateTime createdAt, LocalDateTime updatedAt, double width, double height, String imageService) {
        this.imageId = imageId;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.width = width;
        this.height = height;
        this.imageService = imageService != null ? imageService : "Ximilar";
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getImageService() {
        return imageService;
    }

    public void setImageService(String imageService) {
        this.imageService = imageService;
    }

    public Set<Label> getLabels() {
        return labels;
    }

    public void setLabels(Set<Label> labels) {
        this.labels = labels;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {

            return true;
        }

        if (o == null || getClass() != o.getClass()) {

            return false;
        }

        Image image = (Image) o;

        return Double.compare(image.width, width) == 0 && Double.compare(image.height, height) == 0 && imageId.equals(image.imageId) && imageUrl.equals(image.imageUrl) && createdAt.equals(image.createdAt) && updatedAt.equals(image.updatedAt) && Objects.equals(imageService, image.imageService) && Objects.equals(labels, image.labels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageId, imageUrl, createdAt, updatedAt, width, height, imageService, labels);
    }
}
