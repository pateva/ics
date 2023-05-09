package com.example.demo.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "labels")
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long labelId;

    @Column(name = "label_description")
    private String labelDescription;

    @OneToMany(mappedBy = "label")
    Set<ImageLabel> images = new HashSet<>();

    public Label() {}

    public Label(Long labelId, String labelDescription, Set<ImageLabel> images) {
        this.labelId = labelId;
        this.labelDescription = labelDescription;
        this.images = images;
    }

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    public String getLabelDescription() {
        return labelDescription;
    }

    public void setLabelDescription(String labelDescription) {
        this.labelDescription = labelDescription;
    }

    public Set<ImageLabel> getImages() {
        return images;
    }

    public void setImages(Set<ImageLabel> images) {
        this.images = images;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Label label = (Label) o;
        return Objects.equals(labelId, label.labelId)
                && Objects.equals(labelDescription, label.labelDescription)
                && Objects.equals(images, label.images);
    }

    @Override
    public int hashCode() {
        return Objects.hash(labelId, labelDescription, images);
    }
}
