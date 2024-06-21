package com.javaweb.api;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.Service.IBuildingService;
import com.javaweb.model.BuildingDTO;

@RestController
public class Building {

  @Autowired
  private IBuildingService buildingService;

  @GetMapping(value="/api/building/")
  public List<BuildingDTO> findAll(@RequestParam(value="name", required = false) String name,
                                    @RequestParam(value="floorArea", required = false) String floorArea,
                                    @RequestParam(value="districtId", required = false) String district,
                                    @RequestParam(value="ward", required = false) String ward,
                                    @RequestParam(value = "street", required = false) String street,
                                    @RequestParam(value="numberOfBasement", required = false) Integer numberOfBasement,
                                    @RequestParam(value = "direction", required = false) String direction,
                                    @RequestParam(value = "level", required = false) String level,
                                    @RequestParam(value = "rentFrom", required = false) String rentFrom,
                                    @RequestParam(value = "rentTo", required = false) String rentTo,
                                    @RequestParam(value = "rentPriceFrom", required = false) String rentPriceFrom,
                                    @RequestParam(value = "rentPriceFrom", required = false) String rentPriceTo,
                                    @RequestParam(value = "managerName", required = false) String managerName,
                                    @RequestParam(value = "managerPhoneNumber", required = false) String managerPhoneNumber,
                                    @RequestParam(value = "staffId", required = false) String staffId,
                                    @RequestParam(value="typeCode", required = false) List<String> typeCode) {
    return buildingService.findAll(name, floorArea, district, ward, street, numberOfBasement, direction, level, rentFrom,
                                    rentTo, rentPriceFrom, rentPriceTo, managerName, managerPhoneNumber, staffId, typeCode);
  }
}
