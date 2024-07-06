package com.javaweb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaweb.Repository.Custom.IDistrictRepositoryCustomer;
import com.javaweb.Repository.Entity.DistrictEntity;

public interface IDistrictRepository extends JpaRepository<DistrictEntity, Long>, IDistrictRepositoryCustomer {
    
}
