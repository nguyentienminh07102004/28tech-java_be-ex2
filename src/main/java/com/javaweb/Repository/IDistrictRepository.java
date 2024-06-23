package com.javaweb.Repository;

import java.sql.SQLException;

import com.javaweb.Repository.Entity.DistrictEntity;

public interface IDistrictRepository {
    DistrictEntity findById(Long id) throws ClassNotFoundException, SQLException;
}
