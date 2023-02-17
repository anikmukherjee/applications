package com.points.service;

import com.points.vo.PointsVO;

public interface PointsService {
    public PointsVO getPointsByCustomerId(Long customerId);
}
