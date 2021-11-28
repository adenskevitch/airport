package com.solvd.airport.domain;

import java.util.List;

public class Airport {

    private Long id;
    private String name;
    private Address address;
    private List<Flight> flights;

    public Airport(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public Airport() {
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }
}
