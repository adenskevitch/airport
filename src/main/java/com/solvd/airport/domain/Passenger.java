package com.solvd.airport.domain;

import java.util.List;

public class Passenger {

    private Long id;
    private String name;
    private String surname;
    private String passportNumber;
    private List<Ticket> tickets;

    public Passenger(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Passenger() {
    }

    @Override
    public String toString() {
        return "Passenger{name='" + name + '\'' +
                ", surname='" + surname + '\'' +
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
