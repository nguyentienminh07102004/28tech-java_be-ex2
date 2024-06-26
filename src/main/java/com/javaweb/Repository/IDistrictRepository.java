package com.javaweb.Repository;

import com.javaweb.Repository.Entity.DistrictEntity;

public interface IDistrictRepository {
    DistrictEntity findById(Long id);
}
