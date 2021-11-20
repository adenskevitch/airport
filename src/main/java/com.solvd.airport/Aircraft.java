package com.solvd.airport;

import java.util.List;

public class Aircraft {

    private Long id;
    private String boardNumber;
    private String type;
    private Integer seatsCount;
    private List<Flight> flights;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBoardNumber() {
        return boardNumber;
    }

    public void setBoardNumber(String name) {
        this.boardNumber = boardNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSeatsCount() {
        return seatsCount;
    }

    public void setSeatsCount(Integer seatsCount) {
        this.seatsCount = seatsCount;
    }

    public List<Flight> getFlight() {
        return flights;
    }

    public void setFlight(List<Flight> flights) {
        this.flights = flights;
    }
}
