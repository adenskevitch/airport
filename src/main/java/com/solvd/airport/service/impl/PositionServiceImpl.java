package com.solvd.airport.service.impl;

import com.solvd.airport.domain.Position;
import com.solvd.airport.persistence.PositionRepository;
import com.solvd.airport.persistence.impl.PositionRepositoryImpl;
import com.solvd.airport.service.PositionService;

public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;

    public PositionServiceImpl() {
        this.positionRepository = new PositionRepositoryImpl();
    }

    @Override
    public Position insert(Position position) {
        position.setId(null);
        positionRepository.insert(position);
        return position;
    }
}
