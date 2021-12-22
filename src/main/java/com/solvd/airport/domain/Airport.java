package com.solvd.airport.domain;

import java.util.List;
import java.util.Objects;

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

    @Override
    public String toString() {
        return "Airport{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", flights=" + flights +
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport = (Airport) o;
        return name.equals(airport.name) && address.equals(airport.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address);
    }
}
