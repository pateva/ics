package com.example.demo.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "labels")
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long labelId;

    @Column(name = "label_description")
    private String labelDescription;

    @ManyToMany(mappedBy = "labels")
    private Set<Image> images = new HashSet<>();

    public Label() {}

    public Label(Long labelId, String labelDescription, Set<Image> images) {
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

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }
}
