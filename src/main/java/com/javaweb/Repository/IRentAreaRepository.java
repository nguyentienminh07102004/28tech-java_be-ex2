package com.javaweb.Repository;

import java.sql.SQLException;
import java.util.List;

import com.javaweb.Repository.Entity.RentAreaEntity;

public interface IRentAreaRepository {
	List<RentAreaEntity> findByBuildingId(Long id) throws SQLException, ClassNotFoundException;
}
