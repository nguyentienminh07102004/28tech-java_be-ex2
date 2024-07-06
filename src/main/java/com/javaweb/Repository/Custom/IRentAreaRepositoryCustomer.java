package com.javaweb.Repository.Custom;

import java.util.List;

import com.javaweb.Repository.Entity.RentAreaEntity;

public interface IRentAreaRepositoryCustomer {
	List<RentAreaEntity> findByBuildingId(Long id);
}
