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
 * A Country.
 */
@Document(collection = "country")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "country")
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("code")
    private String code;

    @DBRef
    @Field("province")
    private Set<CountryArea> provinces = new HashSet<>();
    @DBRef
    @Field("territory")
    private Set<CountryArea> territories = new HashSet<>();
    @DBRef
    @Field("countryState")
    private Set<CountryArea> countryStates = new HashSet<>();
    @DBRef
    @Field("city")
    private Set<CountryArea> cities = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Country name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public Country code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<CountryArea> getProvinces() {
        return provinces;
    }

    public Country provinces(Set<CountryArea> countryAreas) {
        this.provinces = countryAreas;
        return this;
    }

    public Country addProvince(CountryArea countryArea) {
        this.provinces.add(countryArea);
        countryArea.setCountry(this);
        return this;
    }

    public Country removeProvince(CountryArea countryArea) {
        this.provinces.remove(countryArea);
        countryArea.setCountry(null);
        return this;
    }

    public void setProvinces(Set<CountryArea> countryAreas) {
        this.provinces = countryAreas;
    }

    public Set<CountryArea> getTerritories() {
        return territories;
    }

    public Country territories(Set<CountryArea> countryAreas) {
        this.territories = countryAreas;
        return this;
    }

    public Country addTerritory(CountryArea countryArea) {
        this.territories.add(countryArea);
        countryArea.setCountry(this);
        return this;
    }

    public Country removeTerritory(CountryArea countryArea) {
        this.territories.remove(countryArea);
        countryArea.setCountry(null);
        return this;
    }

    public void setTerritories(Set<CountryArea> countryAreas) {
        this.territories = countryAreas;
    }

    public Set<CountryArea> getCountryStates() {
        return countryStates;
    }

    public Country countryStates(Set<CountryArea> countryAreas) {
        this.countryStates = countryAreas;
        return this;
    }

    public Country addCountryState(CountryArea countryArea) {
        this.countryStates.add(countryArea);
        countryArea.setCountry(this);
        return this;
    }

    public Country removeCountryState(CountryArea countryArea) {
        this.countryStates.remove(countryArea);
        countryArea.setCountry(null);
        return this;
    }

    public void setCountryStates(Set<CountryArea> countryAreas) {
        this.countryStates = countryAreas;
    }

    public Set<CountryArea> getCities() {
        return cities;
    }

    public Country cities(Set<CountryArea> countryAreas) {
        this.cities = countryAreas;
        return this;
    }

    public Country addCity(CountryArea countryArea) {
        this.cities.add(countryArea);
        countryArea.setCountry(this);
        return this;
    }

    public Country removeCity(CountryArea countryArea) {
        this.cities.remove(countryArea);
        countryArea.setCountry(null);
        return this;
    }

    public void setCities(Set<CountryArea> countryAreas) {
        this.cities = countryAreas;
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
        Country country = (Country) o;
        if (country.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), country.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Country{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", code='" + getCode() + "'" +
            "}";
    }
}
