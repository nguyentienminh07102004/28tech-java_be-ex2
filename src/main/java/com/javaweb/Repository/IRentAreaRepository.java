package com.javaweb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaweb.Repository.Custom.IRentAreaRepositoryCustomer;
import com.javaweb.Repository.Entity.RentAreaEntity;

public interface IRentAreaRepository extends JpaRepository<RentAreaEntity, Long>, IRentAreaRepositoryCustomer {
	
}
