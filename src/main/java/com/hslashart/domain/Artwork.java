package com.hslashart.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.hslashart.domain.enumeration.Currency;

import com.hslashart.domain.enumeration.Availability;

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

    @Field("description")
    private String description;

    @Field("price")
    private BigDecimal price;

    @Field("currency")
    private Currency currency;

    @Field("image")
    private String image;

    @Field("thumbnail")
    private String thumbnail;

    @Field("dimensions")
    private String dimensions;

    @Field("creation_date")
    private LocalDate creationDate;

    @Field("credit_line")
    private String creditLine;

    @Field("copyright_image")
    private String copyrightImage;

    @Field("classification")
    private String classification;

    @Field("availability")
    private Availability availability;

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

    public String getDescription() {
        return description;
    }

    public Artwork description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Artwork price(BigDecimal price) {
        this.price = price;
        return this;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Artwork currency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getImage() {
        return image;
    }

    public Artwork image(String image) {
        this.image = image;
        return this;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public Artwork thumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDimensions() {
        return dimensions;
    }

    public Artwork dimensions(String dimensions) {
        this.dimensions = dimensions;
        return this;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Artwork creationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreditLine() {
        return creditLine;
    }

    public Artwork creditLine(String creditLine) {
        this.creditLine = creditLine;
        return this;
    }

    public void setCreditLine(String creditLine) {
        this.creditLine = creditLine;
    }

    public String getCopyrightImage() {
        return copyrightImage;
    }

    public Artwork copyrightImage(String copyrightImage) {
        this.copyrightImage = copyrightImage;
        return this;
    }

    public void setCopyrightImage(String copyrightImage) {
        this.copyrightImage = copyrightImage;
    }

    public String getClassification() {
        return classification;
    }

    public Artwork classification(String classification) {
        this.classification = classification;
        return this;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public Availability getAvailability() {
        return availability;
    }

    public Artwork availability(Availability availability) {
        this.availability = availability;
        return this;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
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
            ", description='" + getDescription() + "'" +
            ", price=" + getPrice() +
            ", currency='" + getCurrency() + "'" +
            ", image='" + getImage() + "'" +
            ", thumbnail='" + getThumbnail() + "'" +
            ", dimensions='" + getDimensions() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            ", creditLine='" + getCreditLine() + "'" +
            ", copyrightImage='" + getCopyrightImage() + "'" +
            ", classification='" + getClassification() + "'" +
            ", availability='" + getAvailability() + "'" +
            "}";
    }
}
