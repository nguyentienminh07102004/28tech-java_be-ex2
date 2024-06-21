package com.javaweb.Service.IMPL;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.Repository.IBuildingRepository;
import com.javaweb.Repository.Entity.BuildingEntity;
import com.javaweb.Service.IBuildingService;
import com.javaweb.model.BuildingDTO;

@Service
public class BuildingService implements IBuildingService {
  @Autowired
  private IBuildingRepository buildingRepository;
  @Override
  public List<BuildingDTO> findAll(String name, String floorArea, String district, String ward, String street, Integer numberOfBasement, String direction, String level, String rentFrom,
  String rentTo, String rentPriceFrom, String rentPriceTo, String managerName,String  managerPhoneNumber, String staffId, List<String> typeCode) {
    List<BuildingEntity> list = buildingRepository.findAll(name, floorArea, district, ward, street, numberOfBasement, direction, level, rentFrom,
    rentTo, rentPriceFrom, rentPriceTo, managerName, managerPhoneNumber, staffId, typeCode);
    List<BuildingDTO> result = new ArrayList<BuildingDTO>();
    for(BuildingEntity buildingEntity : list) {
      BuildingDTO building = new BuildingDTO();
      building.setName(buildingEntity.getName());
      building.setNumberOfBasement(buildingEntity.getNumberOfBasement());
      building.setAddress(buildingEntity.getStreet() + ", " + buildingEntity.getWard());
      result.add(building);
    }
    return result;
  }
  
}
