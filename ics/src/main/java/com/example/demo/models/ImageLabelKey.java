package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ImageLabelKey implements Serializable {
    @Column(name = "image_id")
    private long image_id;

    @Column(name = "label_id")
    private long label_id;

    public ImageLabelKey(long image_id, long label_id) {
        this.image_id = image_id;
        this.label_id = label_id;
    }

    public long getImage_id() {
        return image_id;
    }

    public void setImage_id(long image_id) {
        this.image_id = image_id;
    }

    public long getLabel_id() {
        return label_id;
    }

    public void setLabel_id(long label_id) {
        this.label_id = label_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageLabelKey that = (ImageLabelKey) o;
        return image_id == that.image_id && label_id == that.label_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(image_id, label_id);
    }
}
