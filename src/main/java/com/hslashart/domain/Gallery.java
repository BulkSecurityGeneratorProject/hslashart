package com.hslashart.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.time.LocalDate;
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

    @Field("creation_date")
    private LocalDate creationDate;

    @Field("description")
    private String description;

    @Field("order")
    private Integer order;

    @DBRef
    @Field("artworks")
    private Set<Artwork> artworks = new HashSet<>();

    @DBRef
    @Field("artist")
    @JsonIgnoreProperties("")
    private Artist artist;

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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Gallery creationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public Gallery description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOrder() {
        return order;
    }

    public Gallery order(Integer order) {
        this.order = order;
        return this;
    }

    public void setOrder(Integer order) {
        this.order = order;
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

    public Artist getArtist() {
        return artist;
    }

    public Gallery artist(Artist artist) {
        this.artist = artist;
        return this;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
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
            ", creationDate='" + getCreationDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", order=" + getOrder() +
            "}";
    }
}
