package com.javaweb.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.javaweb.model.BuildingDTO;

public interface IBuildingService {
	List<BuildingDTO> findAll(Map<String, String> params, List<String> typeCode)
		throws SQLException, NumberFormatException, ClassNotFoundException;
}
