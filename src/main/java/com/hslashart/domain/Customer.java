package com.hslashart.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.Objects;

import com.hslashart.domain.enumeration.Gender;

/**
 * A Customer.
 */
@Document(collection = "customer")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("first_name")
    private String firstName;

    @Field("last_name")
    private String lastName;

    @Field("gender_other")
    private String genderOther;

    @Field("phone_main")
    private String phoneMain;

    @Field("phone_mobile")
    private String phoneMobile;

    @Field("shipping_last_name")
    private String shippingLastName;

    @Field("shipping_first_name")
    private String shippingFirstName;

    @Field("shipping_gender_other")
    private String shippingGenderOther;

    @Field("shipping_address_line_1")
    private String shippingAddressLine1;

    @Field("shipping_address_line_2")
    private String shippingAddressLine2;

    @Field("shipping_city")
    private String shippingCity;

    @Field("shipping_commentary")
    private String shippingCommentary;

    @Field("billing_last_name")
    private String billingLastName;

    @Field("billing_first_name")
    private String billingFirstName;

    @Field("billing_gender_other")
    private String billingGenderOther;

    @Field("billing_address_line_1")
    private String billingAddressLine1;

    @Field("billing_address_line_2")
    private String billingAddressLine2;

    @Field("shipping_postal_code")
    private String shippingPostalCode;

    @Field("billing_postal_code")
    private String billingPostalCode;

    @Field("shipping_country")
    private String shippingCountry;

    @Field("shipping_province")
    private String shippingProvince;

    @Field("shipping_country_state")
    private String shippingCountryState;

    @Field("shipping_territory")
    private String shippingTerritory;

    @Field("billing_city")
    private String billingCity;

    @Field("billing_country")
    private String billingCountry;

    @Field("billing_province")
    private String billingProvince;

    @Field("billing_territory")
    private String billingTerritory;

    @Field("billing_country_state")
    private String billingCountryState;

    @Field("gender")
    private Gender gender;

    @Field("shipping_gender")
    private Gender shippingGender;

    @Field("billing_gender")
    private Gender billingGender;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public Customer firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Customer lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGenderOther() {
        return genderOther;
    }

    public Customer genderOther(String genderOther) {
        this.genderOther = genderOther;
        return this;
    }

    public void setGenderOther(String genderOther) {
        this.genderOther = genderOther;
    }

    public String getPhoneMain() {
        return phoneMain;
    }

    public Customer phoneMain(String phoneMain) {
        this.phoneMain = phoneMain;
        return this;
    }

    public void setPhoneMain(String phoneMain) {
        this.phoneMain = phoneMain;
    }

    public String getPhoneMobile() {
        return phoneMobile;
    }

    public Customer phoneMobile(String phoneMobile) {
        this.phoneMobile = phoneMobile;
        return this;
    }

    public void setPhoneMobile(String phoneMobile) {
        this.phoneMobile = phoneMobile;
    }

    public String getShippingLastName() {
        return shippingLastName;
    }

    public Customer shippingLastName(String shippingLastName) {
        this.shippingLastName = shippingLastName;
        return this;
    }

    public void setShippingLastName(String shippingLastName) {
        this.shippingLastName = shippingLastName;
    }

    public String getShippingFirstName() {
        return shippingFirstName;
    }

    public Customer shippingFirstName(String shippingFirstName) {
        this.shippingFirstName = shippingFirstName;
        return this;
    }

    public void setShippingFirstName(String shippingFirstName) {
        this.shippingFirstName = shippingFirstName;
    }

    public String getShippingGenderOther() {
        return shippingGenderOther;
    }

    public Customer shippingGenderOther(String shippingGenderOther) {
        this.shippingGenderOther = shippingGenderOther;
        return this;
    }

    public void setShippingGenderOther(String shippingGenderOther) {
        this.shippingGenderOther = shippingGenderOther;
    }

    public String getShippingAddressLine1() {
        return shippingAddressLine1;
    }

    public Customer shippingAddressLine1(String shippingAddressLine1) {
        this.shippingAddressLine1 = shippingAddressLine1;
        return this;
    }

    public void setShippingAddressLine1(String shippingAddressLine1) {
        this.shippingAddressLine1 = shippingAddressLine1;
    }

    public String getShippingAddressLine2() {
        return shippingAddressLine2;
    }

    public Customer shippingAddressLine2(String shippingAddressLine2) {
        this.shippingAddressLine2 = shippingAddressLine2;
        return this;
    }

    public void setShippingAddressLine2(String shippingAddressLine2) {
        this.shippingAddressLine2 = shippingAddressLine2;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public Customer shippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
        return this;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public String getShippingCommentary() {
        return shippingCommentary;
    }

    public Customer shippingCommentary(String shippingCommentary) {
        this.shippingCommentary = shippingCommentary;
        return this;
    }

    public void setShippingCommentary(String shippingCommentary) {
        this.shippingCommentary = shippingCommentary;
    }

    public String getBillingLastName() {
        return billingLastName;
    }

    public Customer billingLastName(String billingLastName) {
        this.billingLastName = billingLastName;
        return this;
    }

    public void setBillingLastName(String billingLastName) {
        this.billingLastName = billingLastName;
    }

    public String getBillingFirstName() {
        return billingFirstName;
    }

    public Customer billingFirstName(String billingFirstName) {
        this.billingFirstName = billingFirstName;
        return this;
    }

    public void setBillingFirstName(String billingFirstName) {
        this.billingFirstName = billingFirstName;
    }

    public String getBillingGenderOther() {
        return billingGenderOther;
    }

    public Customer billingGenderOther(String billingGenderOther) {
        this.billingGenderOther = billingGenderOther;
        return this;
    }

    public void setBillingGenderOther(String billingGenderOther) {
        this.billingGenderOther = billingGenderOther;
    }

    public String getBillingAddressLine1() {
        return billingAddressLine1;
    }

    public Customer billingAddressLine1(String billingAddressLine1) {
        this.billingAddressLine1 = billingAddressLine1;
        return this;
    }

    public void setBillingAddressLine1(String billingAddressLine1) {
        this.billingAddressLine1 = billingAddressLine1;
    }

    public String getBillingAddressLine2() {
        return billingAddressLine2;
    }

    public Customer billingAddressLine2(String billingAddressLine2) {
        this.billingAddressLine2 = billingAddressLine2;
        return this;
    }

    public void setBillingAddressLine2(String billingAddressLine2) {
        this.billingAddressLine2 = billingAddressLine2;
    }

    public String getShippingPostalCode() {
        return shippingPostalCode;
    }

    public Customer shippingPostalCode(String shippingPostalCode) {
        this.shippingPostalCode = shippingPostalCode;
        return this;
    }

    public void setShippingPostalCode(String shippingPostalCode) {
        this.shippingPostalCode = shippingPostalCode;
    }

    public String getBillingPostalCode() {
        return billingPostalCode;
    }

    public Customer billingPostalCode(String billingPostalCode) {
        this.billingPostalCode = billingPostalCode;
        return this;
    }

    public void setBillingPostalCode(String billingPostalCode) {
        this.billingPostalCode = billingPostalCode;
    }

    public String getShippingCountry() {
        return shippingCountry;
    }

    public Customer shippingCountry(String shippingCountry) {
        this.shippingCountry = shippingCountry;
        return this;
    }

    public void setShippingCountry(String shippingCountry) {
        this.shippingCountry = shippingCountry;
    }

    public String getShippingProvince() {
        return shippingProvince;
    }

    public Customer shippingProvince(String shippingProvince) {
        this.shippingProvince = shippingProvince;
        return this;
    }

    public void setShippingProvince(String shippingProvince) {
        this.shippingProvince = shippingProvince;
    }

    public String getShippingCountryState() {
        return shippingCountryState;
    }

    public Customer shippingCountryState(String shippingCountryState) {
        this.shippingCountryState = shippingCountryState;
        return this;
    }

    public void setShippingCountryState(String shippingCountryState) {
        this.shippingCountryState = shippingCountryState;
    }

    public String getShippingTerritory() {
        return shippingTerritory;
    }

    public Customer shippingTerritory(String shippingTerritory) {
        this.shippingTerritory = shippingTerritory;
        return this;
    }

    public void setShippingTerritory(String shippingTerritory) {
        this.shippingTerritory = shippingTerritory;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public Customer billingCity(String billingCity) {
        this.billingCity = billingCity;
        return this;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingCountry() {
        return billingCountry;
    }

    public Customer billingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
        return this;
    }

    public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }

    public String getBillingProvince() {
        return billingProvince;
    }

    public Customer billingProvince(String billingProvince) {
        this.billingProvince = billingProvince;
        return this;
    }

    public void setBillingProvince(String billingProvince) {
        this.billingProvince = billingProvince;
    }

    public String getBillingTerritory() {
        return billingTerritory;
    }

    public Customer billingTerritory(String billingTerritory) {
        this.billingTerritory = billingTerritory;
        return this;
    }

    public void setBillingTerritory(String billingTerritory) {
        this.billingTerritory = billingTerritory;
    }

    public String getBillingCountryState() {
        return billingCountryState;
    }

    public Customer billingCountryState(String billingCountryState) {
        this.billingCountryState = billingCountryState;
        return this;
    }

    public void setBillingCountryState(String billingCountryState) {
        this.billingCountryState = billingCountryState;
    }

    public Gender getGender() {
        return gender;
    }

    public Customer gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Gender getShippingGender() {
        return shippingGender;
    }

    public Customer shippingGender(Gender shippingGender) {
        this.shippingGender = shippingGender;
        return this;
    }

    public void setShippingGender(Gender shippingGender) {
        this.shippingGender = shippingGender;
    }

    public Gender getBillingGender() {
        return billingGender;
    }

    public Customer billingGender(Gender billingGender) {
        this.billingGender = billingGender;
        return this;
    }

    public void setBillingGender(Gender billingGender) {
        this.billingGender = billingGender;
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
        Customer customer = (Customer) o;
        if (customer.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), customer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Customer{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", genderOther='" + getGenderOther() + "'" +
            ", phoneMain='" + getPhoneMain() + "'" +
            ", phoneMobile='" + getPhoneMobile() + "'" +
            ", shippingLastName='" + getShippingLastName() + "'" +
            ", shippingFirstName='" + getShippingFirstName() + "'" +
            ", shippingGenderOther='" + getShippingGenderOther() + "'" +
            ", shippingAddressLine1='" + getShippingAddressLine1() + "'" +
            ", shippingAddressLine2='" + getShippingAddressLine2() + "'" +
            ", shippingCity='" + getShippingCity() + "'" +
            ", shippingCommentary='" + getShippingCommentary() + "'" +
            ", billingLastName='" + getBillingLastName() + "'" +
            ", billingFirstName='" + getBillingFirstName() + "'" +
            ", billingGenderOther='" + getBillingGenderOther() + "'" +
            ", billingAddressLine1='" + getBillingAddressLine1() + "'" +
            ", billingAddressLine2='" + getBillingAddressLine2() + "'" +
            ", shippingPostalCode='" + getShippingPostalCode() + "'" +
            ", billingPostalCode='" + getBillingPostalCode() + "'" +
            ", shippingCountry='" + getShippingCountry() + "'" +
            ", shippingProvince='" + getShippingProvince() + "'" +
            ", shippingCountryState='" + getShippingCountryState() + "'" +
            ", shippingTerritory='" + getShippingTerritory() + "'" +
            ", billingCity='" + getBillingCity() + "'" +
            ", billingCountry='" + getBillingCountry() + "'" +
            ", billingProvince='" + getBillingProvince() + "'" +
            ", billingTerritory='" + getBillingTerritory() + "'" +
            ", billingCountryState='" + getBillingCountryState() + "'" +
            ", gender='" + getGender() + "'" +
            ", shippingGender='" + getShippingGender() + "'" +
            ", billingGender='" + getBillingGender() + "'" +
            "}";
    }
}
