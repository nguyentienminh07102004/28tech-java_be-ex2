package com.javaweb.Repository;

import java.util.List;

import com.javaweb.Repository.Entity.RentAreaEntity;

public interface IRentAreaRepository {
	List<RentAreaEntity> findByBuildingId(Long id);
}
