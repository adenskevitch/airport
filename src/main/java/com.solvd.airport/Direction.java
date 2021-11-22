package com.solvd.airport;

import java.util.Date;

public class Direction {

    private Airport airport;
    private Date time;

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
