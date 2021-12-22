package com.solvd.airport.domain;

import java.util.List;
import java.util.Objects;

public class Airline {

    private Long id;
    private String name;
    private String country;
    private List<Aircraft> aircrafts;
    private List<Employee> employees;

    public Airline(String airlineName, String airlineCountry) {
        this.name = airlineName;
        this.country = airlineCountry;
    }

    @Override
    public String toString() {
        return "Airline{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", aircrafts=" + aircrafts +
                ", employees=" + employees +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Aircraft> getAircrafts() {
        return aircrafts;
    }

    public void setAircrafts(List<Aircraft> aircrafts) {
        this.aircrafts = aircrafts;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airline airline = (Airline) o;
        return name.equals(airline.name) && country.equals(airline.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country);
    }
}
