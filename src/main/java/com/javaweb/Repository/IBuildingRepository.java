package com.javaweb.Repository;

import java.util.List;

import com.javaweb.Repository.Entity.BuildingEntity;

public interface IBuildingRepository {
  List<BuildingEntity> findAll(String name, String floorArea, String district, String ward, String street, Integer numberOfBasement, String direction, String level, String rentFrom,
  String rentTo, String rentPriceFrom, String rentPriceTo, String managerName,String  managerPhoneNumber, String staffId, List<String> typeCode);
}
