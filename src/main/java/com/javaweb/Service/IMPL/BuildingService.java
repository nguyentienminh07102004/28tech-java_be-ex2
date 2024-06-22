package com.javaweb.Service.IMPL;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
  public List<BuildingDTO> findAll(Map<String, String> params, List<String> typeCode) {
    List<BuildingEntity> list = buildingRepository.findAll(params, typeCode);
    List<BuildingDTO> result = new ArrayList<BuildingDTO>();
    for(BuildingEntity buildingEntity : list) {
      BuildingDTO building = new BuildingDTO();
      building.setName(buildingEntity.getName());
      building.setNumberOfBasement(buildingEntity.getNumberOfBasement());
      building.setAddress(buildingEntity.getStreet() + ", " + buildingEntity.getWard() + ", " + buildingEntity.getDistrict());
      building.setManagerName(buildingEntity.getManagerName());
      building.setManagerPhoneNumber(buildingEntity.getManagerPhoneNumber());
      building.setFloorArea(buildingEntity.getFloorArea());
      building.setRentPrice(buildingEntity.getRentPrice());
      building.setBrokerageFee(buildingEntity.getBrokerageFee());
      building.setServiceFee(buildingEntity.getServiceFee());
      building.setRentArea(buildingEntity.getRentArea());
      result.add(building);
    }
    return result;
  }
  
}
