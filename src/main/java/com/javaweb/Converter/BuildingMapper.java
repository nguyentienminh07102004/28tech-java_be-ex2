package com.javaweb.Converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.Repository.Entity.BuildingEntity;
import com.javaweb.Repository.Entity.DistrictEntity;
import com.javaweb.Repository.Entity.RentAreaEntity;
import com.javaweb.model.BuildingDTO;

@Component
public class BuildingMapper {

	@Autowired
	private ModelMapper modelMapper;

	public BuildingDTO entityToDTO(BuildingEntity buildingEntity) {
		// Tự động Mapper dữ liệu sang với các field giống nhau về cả tên và kiểu dữ liệu
		BuildingDTO building = modelMapper.map(buildingEntity, BuildingDTO.class);
		DistrictEntity districtEntity = buildingEntity.getDistrict();
		building.setAddress(buildingEntity.getStreet() + ", " + buildingEntity.getWard() + ", " + districtEntity.getName());
		List<RentAreaEntity> rentAreaList = buildingEntity.getRentArea();
		String rentArea = rentAreaList.stream().map(RentAreaEntity::getValue).map(item -> String.valueOf(item)).collect(Collectors.joining(","));
		building.setRentArea(rentArea);
		return building;
	}
}
