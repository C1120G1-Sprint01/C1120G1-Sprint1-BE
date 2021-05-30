package com.c1120g1.adweb.service.impl;

import com.c1120g1.adweb.entity.Position;
import com.c1120g1.adweb.repository.PositionRepository;
import com.c1120g1.adweb.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    private PositionRepository positionRepository;

    /**
     * Method: get all position
     * Author: HanTH
     *
     * @return
     */
    @Override
    public List<Position> showAllPosition() {
        return positionRepository.showAllPosition();
    }
}
