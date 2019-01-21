package com.hslashart.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Artwork.
 */
@Document(collection = "artwork")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "artwork")
public class Artwork implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("title")
    private String title;

    @DBRef
    @Field("galleries")
    @JsonIgnore
    private Set<Gallery> galleries = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Artwork title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Gallery> getGalleries() {
        return galleries;
    }

    public Artwork galleries(Set<Gallery> galleries) {
        this.galleries = galleries;
        return this;
    }

    public Artwork addGallery(Gallery gallery) {
        this.galleries.add(gallery);
        gallery.getArtworks().add(this);
        return this;
    }

    public Artwork removeGallery(Gallery gallery) {
        this.galleries.remove(gallery);
        gallery.getArtworks().remove(this);
        return this;
    }

    public void setGalleries(Set<Gallery> galleries) {
        this.galleries = galleries;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Artwork artwork = (Artwork) o;
        if (artwork.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), artwork.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Artwork{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            "}";
    }
}
