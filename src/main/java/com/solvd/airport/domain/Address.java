package com.solvd.airport.domain;

public class Address {

    private Long id;
    private String country;
    private String locality;
    private String street;
    private String buildNumber;

    public Address(String country, String locality) {
        this.country = country;
        this.locality = locality;
    }

    public Address() {
    }

    @Override
    public String toString() {
        return "Address{" +
                "country='" + country + '\'' +
                ", locality='" + locality + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(String buildNumber) {
        this.buildNumber = buildNumber;
    }
}
