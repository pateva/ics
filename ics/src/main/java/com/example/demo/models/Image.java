package com.example.demo.models;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;


import jakarta.persistence.*;
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

    @Column(name = "\"image_size\"")
    private double size;

    @Column(name = "\"image_service\"")
    private String imageService;

    @ManyToMany
    @JoinTable(
            name = "\"image_label\"",
            joinColumns = @JoinColumn(name = "\"image_id\""),
            inverseJoinColumns = @JoinColumn(name = "\"label_id\""))
    Set<Label> labels;

    public Image() {}

    public Image(Long imageId, String imageUrl, LocalDateTime createdAt, LocalDateTime updatedAt, double size, String imageService) {
        this.imageId = imageId;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.size = size;
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

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getImageService() {
        return imageService;
    }

    public void setImageService(String imageService) {
        this.imageService = imageService;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Double.compare(image.size, size) == 0
                && Objects.equals(imageId, image.imageId)
                && Objects.equals(imageUrl, image.imageUrl)
                && Objects.equals(createdAt, image.createdAt)
                && Objects.equals(updatedAt, image.updatedAt)
                && Objects.equals(imageService, image.imageService);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageId, imageUrl, createdAt, updatedAt, size, imageService);
    }
}
