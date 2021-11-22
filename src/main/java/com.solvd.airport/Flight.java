package com.solvd.airport;

public class Flight {

    private Long id;
    private Integer number;
    private Direction from;
    private Direction to;
    private Aircraft aircraft;

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

    public Direction getFrom() {
        return from;
    }

    public void setFrom(Direction from) {
        this.from = from;
    }

    public Direction getTo() {
        return to;
    }

    public void setTo(Direction to) {
        this.to = to;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }
}
