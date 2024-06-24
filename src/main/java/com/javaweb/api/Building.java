package com.javaweb.api;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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
	public List<BuildingDTO> findAll(@RequestParam(value = "typeCode", required = false) List<String> typeCode, @RequestParam Map<String, String> params) throws SQLException, ClassNotFoundException, NumberFormatException {
	return buildingService.findAll(params, typeCode);
	}
}
