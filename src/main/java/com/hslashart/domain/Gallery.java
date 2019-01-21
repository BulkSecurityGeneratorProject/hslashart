package com.hslashart.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Gallery.
 */
@Document(collection = "gallery")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "gallery")
public class Gallery implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("title")
    private String title;

    @DBRef
    @Field("artworks")
    private Set<Artwork> artworks = new HashSet<>();

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

    public Gallery title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Artwork> getArtworks() {
        return artworks;
    }

    public Gallery artworks(Set<Artwork> artworks) {
        this.artworks = artworks;
        return this;
    }

    public Gallery addArtwork(Artwork artwork) {
        this.artworks.add(artwork);
        artwork.getGalleries().add(this);
        return this;
    }

    public Gallery removeArtwork(Artwork artwork) {
        this.artworks.remove(artwork);
        artwork.getGalleries().remove(this);
        return this;
    }

    public void setArtworks(Set<Artwork> artworks) {
        this.artworks = artworks;
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
        Gallery gallery = (Gallery) o;
        if (gallery.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), gallery.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Gallery{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            "}";
    }
}
