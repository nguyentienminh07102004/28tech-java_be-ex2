package com.javaweb.Repository.Custom;

import java.util.List;

import com.javaweb.Builder.BuildingSearchBuilder;
import com.javaweb.Repository.Entity.BuildingEntity;

public interface IBuildingRepositoryCustomer {
	List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder);
}
