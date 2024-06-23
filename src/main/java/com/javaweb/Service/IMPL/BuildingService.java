package com.javaweb.Service.IMPL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.Repository.IBuildingRepository;
import com.javaweb.Repository.IDistrictRepository;
import com.javaweb.Repository.Entity.BuildingEntity;
import com.javaweb.Service.IBuildingService;
import com.javaweb.model.BuildingDTO;

@Service
public class BuildingService implements IBuildingService {

  @Autowired
  private IBuildingRepository buildingRepository;

  @Autowired
  private IDistrictRepository districtRepository;

  @Override
  public List<BuildingDTO> findAll(Map<String, String> params, List<String> typeCode)
      throws SQLException, ClassNotFoundException, NumberFormatException {
    List<BuildingEntity> list = buildingRepository.findAll(params, typeCode);
    List<BuildingDTO> result = new ArrayList<BuildingDTO>();
    for (BuildingEntity buildingEntity : list) {
      BuildingDTO building = new BuildingDTO();
      building.setName(buildingEntity.getName());
      building.setAddress(buildingEntity.getStreet() + ", " + buildingEntity.getWard() + ", " + districtRepository.findById(buildingEntity.getDistrictId()).getName());
      building.setBrokerageFee(buildingEntity.getBrokerageFee());
      building.setServiceFee(buildingEntity.getServiceFee());
      building.setFloorArea(buildingEntity.getFloorArea());
      building.setManagerName(buildingEntity.getManagerName());
      building.setManagerPhoneNumber(buildingEntity.getManagerPhoneNumber());
      building.setRentPrice(buildingEntity.getRentPrice());
      building.setNumberOfBasement(buildingEntity.getNumberOfBasement());
      result.add(building);
    }
    return result;
  }

}
