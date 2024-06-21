package com.javaweb.Service;

import java.util.List;

import com.javaweb.model.BuildingDTO;

public interface IBuildingService {
  List<BuildingDTO> findAll(String name, String floorArea, String district, String ward, String street, Integer numberOfBasement, String direction, String level, String rentFrom,
  String rentTo, String rentPriceFrom, String rentPriceTo, String managerName,String  managerPhoneNumber, String staffId, List<String> typeCode);
}
