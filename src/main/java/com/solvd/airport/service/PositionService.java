package com.solvd.airport.service;

import com.solvd.airport.domain.Position;

import java.util.List;

public interface PositionService {

    Position create(Position position);

    List<Position> createList(List<Position> positionList);

}
