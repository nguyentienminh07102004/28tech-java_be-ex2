package com.javaweb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaweb.Repository.Custom.IBuildingRepositoryCustomer;
import com.javaweb.Repository.Entity.BuildingEntity;

public interface IBuildingRepository extends JpaRepository<BuildingEntity, Long>, IBuildingRepositoryCustomer {
	
}
