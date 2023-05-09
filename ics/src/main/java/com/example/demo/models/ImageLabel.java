package com.example.demo.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "image_label")
public class ImageLabel {
    @EmbeddedId
    ImageLabelKey id;

    @ManyToOne
    @MapsId("imageId")
    @JoinColumn(name = "image_id")
    Image image;

    @ManyToOne
    @MapsId("labelId")
    @JoinColumn(name = "label_id")
    Label label;

    public ImageLabel(ImageLabelKey id, Image image, Label label) {
        this.id = id;
        this.image = image;
        this.label = label;
    }

    public ImageLabelKey getId() {
        return id;
    }

    public void setId(ImageLabelKey id) {
        this.id = id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }
}
