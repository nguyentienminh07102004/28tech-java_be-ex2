package com.javaweb.api;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.Service.IBuildingService;
import com.javaweb.model.BuildingDTO;


@RestController
public class Building {

	@Autowired
	private IBuildingService buildingService;

	@GetMapping(value="/api/building/")
	public List<BuildingDTO> findAll(@RequestParam(value = "typeCode", required = false) List<String> typeCode, @RequestParam Map<String, String> params) {
		return buildingService.findAll(params, typeCode);
	}

	@PostMapping(value = "/api/building/")
	public void addBuilding(@RequestBody Map<String, String> building) {
		for(Entry<String, String> entry : building.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}
}
