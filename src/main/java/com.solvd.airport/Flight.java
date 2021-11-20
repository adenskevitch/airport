package com.solvd.airport;

import java.util.Date;
import java.util.List;

public class Flight {

    private Long id;
    private Integer number;
    private Airport departureAirport;
    private Airport destinationAirport;
    private Date flightTime;
    private Double cost;
    private List<Aircraft> aircrafts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public Date getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(Date flightTime) {
        this.flightTime = flightTime;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public List<Aircraft> getAircrafts() {
        return aircrafts;
    }

    public void setAircraft(List<Aircraft> aircrafts) {
        this.aircrafts = aircrafts;
    }
}
