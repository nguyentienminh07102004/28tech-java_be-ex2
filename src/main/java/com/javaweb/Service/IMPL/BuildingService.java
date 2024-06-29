package com.javaweb.Service.IMPL;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.Converter.BuildingMapper;
import com.javaweb.Converter.BuildingSearchBuilderConvertor;
import com.javaweb.Repository.IBuildingRepository;
import com.javaweb.Repository.Entity.BuildingEntity;
import com.javaweb.Service.IBuildingService;
import com.javaweb.model.BuildingDTO;

@Service
public class BuildingService implements IBuildingService {

	@Autowired
	private IBuildingRepository buildingRepository;

	@Autowired
	private BuildingMapper entityMapperDTO;

	@Autowired
	private BuildingSearchBuilderConvertor buildingSearchBuilderConvertor;

  	@Override
	public List<BuildingDTO> findAll(Map<String, String> params, List<String> typeCode) {
		List<BuildingEntity> list = buildingRepository.findAll(buildingSearchBuilderConvertor.toBuildingSearchBuilder(params, typeCode));
		List<BuildingDTO> result = new ArrayList<BuildingDTO>();	
		for (BuildingEntity buildingEntity : list) {
			BuildingDTO building = entityMapperDTO.entityToDTO(buildingEntity);
			result.add(building);
		}
		return result;
	}

}
