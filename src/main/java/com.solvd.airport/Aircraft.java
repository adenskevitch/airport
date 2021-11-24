package com.solvd.airport;

public class Aircraft {

    private Long id;
    private String boardNumber;
    private String type;
    private Integer seatsCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBoardNumber() {
        return boardNumber;
    }

    public void setBoardNumber(String boardNumber) {
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
}
