package com.example.demo.models;




import jakarta.persistence.*;

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

    @ManyToMany(mappedBy = "labels")
    Set<Image> images;

    public Label() {}

    public Label(Long labelId, String labelDescription) {
        this.labelId = labelId;
        this.labelDescription = labelDescription;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Label label = (Label) o;
        return Objects.equals(labelId, label.labelId)
                && Objects.equals(labelDescription, label.labelDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(labelId, labelDescription); //images);
    }
}
