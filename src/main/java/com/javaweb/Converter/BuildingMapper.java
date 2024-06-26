package com.javaweb.Converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.Repository.IDistrictRepository;
import com.javaweb.Repository.IRentAreaRepository;
import com.javaweb.Repository.Entity.BuildingEntity;
import com.javaweb.Repository.Entity.RentAreaEntity;
import com.javaweb.model.BuildingDTO;

@Component
public class BuildingMapper {

	@Autowired
	private IDistrictRepository districtRepository;

	@Autowired
	private IRentAreaRepository rentAreaRepository;

	@Autowired
	private ModelMapper modelMapper;

	public BuildingDTO entityToDTO(BuildingEntity buildingEntity) {
		// Tự động Mapper dữ liệu sang với các field giống nhau về cả tên và kiểu dữ liệu
		BuildingDTO building = modelMapper.map(buildingEntity, BuildingDTO.class);
		building.setAddress(buildingEntity.getStreet() + ", " + buildingEntity.getWard() + ", " + districtRepository.findById(buildingEntity.getDistrictId()).getName());
		List<RentAreaEntity> rentAreaList = rentAreaRepository.findByBuildingId(buildingEntity.getId());
		String rentArea = rentAreaList.stream().map(RentAreaEntity::getValue).map(item -> String.valueOf(item)).collect(Collectors.joining(","));
		building.setRentArea(rentArea);
		return building;
	}
}
