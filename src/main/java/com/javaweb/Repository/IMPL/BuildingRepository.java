package com.javaweb.Repository.IMPL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.javaweb.Repository.IBuildingRepository;
import com.javaweb.Repository.Entity.BuildingEntity;
import com.javaweb.Utils.ConnectionJDBC;
import com.javaweb.Utils.validateDataInput;

@Repository
public class BuildingRepository implements IBuildingRepository {

  @Override
  public List<BuildingEntity> findAll(Map<String, String> params, List<String> typeCode) {
	try {
		StringBuilder sql = new StringBuilder("SELECT b.id, b.name, b.districtid, b.street, b.floorarea, b.ward, b.numberOfBasement, b.managerName, b.managerPhoneNumber, ");
		sql.append("b.rentprice, b.servicefee, b.brokeragefee FROM building b ");
		joinTable(params, typeCode, sql);
		where_condition_normal(params, sql);
		where_condition_special(sql, typeCode, params);
		Connection connection = ConnectionJDBC.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql.toString());
		System.out.println(sql.toString());
		ResultSet resultSet = statement.executeQuery();
		List<BuildingEntity> results = new ArrayList<BuildingEntity>();
		while(resultSet.next()) {
			BuildingEntity buildingEntity = new BuildingEntity();
			buildingEntity.setId(resultSet.getLong("id"));
			buildingEntity.setName(resultSet.getString("name"));
			buildingEntity.setStreet(resultSet.getString("street"));
			buildingEntity.setWard(resultSet.getString("ward"));
			buildingEntity.setServiceFee(resultSet.getString("servicefee"));
			buildingEntity.setBrokerageFee(resultSet.getString("brokeragefee"));
			buildingEntity.setDistrictId(resultSet.getLong("districtid"));
			buildingEntity.setRentPrice(resultSet.getInt("rentprice"));
			buildingEntity.setFloorArea(resultSet.getInt("floorArea"));
			buildingEntity.setManagerName(resultSet.getString("managername"));
			buildingEntity.setManagerPhoneNumber(resultSet.getString("managerphonenumber"));
			results.add(buildingEntity);
		}
		return results;
	} catch (SQLException ex) {
		System.err.println(ex.getMessage());
		return null;
	}
  }

  private void joinTable(Map<String, String> params, List<String> typeCode, StringBuilder sql) {
    if(typeCode != null && typeCode.size() > 0) {
		sql.append(" INNER JOIN buildingrenttype ON buildingrenttype.buildingId = b.id ");
		sql.append(" INNER JOIN renttype ON renttype.id = buildingrenttype.renttypeId ");
	}
	if(validateDataInput.StringValidate(params.get("staffId"))) {
		sql.append(" INNER JOIN assignmentbuilding ON b.id = assignmentbuilding.buildingid ");
	}
  }

  private void where_condition_normal(Map<String, String> params, StringBuilder sql) {
	sql.append(" WHERE 1 = 1 ");
	for(Map.Entry<String, String> param : params.entrySet()) {
		String key = param.getKey().trim();
		if(validateDataInput.StringValidate(key) && !key.toLowerCase().startsWith("area")) {
			if(key.equalsIgnoreCase("numberOfBasement")) {
				Integer.parseInt(param.getValue());
				sql.append(" AND b.numberofbasement = " + param.getValue());
			}
			else if(key.equalsIgnoreCase("staffId")) {
				sql.append(" AND assignmentbuilding.staffid = " + param.getValue());
			}
			else if(key.startsWith("rentPrice")) {
				Integer.parseInt(param.getValue());
				if(key.endsWith("From")) {
					sql.append(" AND b.rentPrice >= " + param.getValue());
				} else {
					sql.append(" AND b.rentPrice <= " + param.getValue());
				}
			}
			else if(!key.equalsIgnoreCase("typeCode")){
				sql.append(" AND b." + key + " LIKE '%" + param.getValue() + "%'");
			}
		}
	}
  }

  private void where_condition_special(StringBuilder sql, List<String> typeCode, Map<String, String> params) {
	if(validateDataInput.StringValidate(params.get("areaFrom")) || validateDataInput.StringValidate(params.get("areaTo"))) {
		sql.append(" AND EXISTS (SELECT rentarea.value FROM rentarea WHERE rentarea.buildingid = b.id ");
		if(validateDataInput.StringValidate(params.get("areaFrom"))) {
			Integer value  = Integer.parseInt(params.get("areaFrom"));
			sql.append(" AND rentarea.value >= ").append(value);
		}
		if(validateDataInput.StringValidate(params.get("areaTo"))) {
			Integer value = Integer.parseInt(params.get("areaTo"));
			sql.append(" AND rentarea.value <= ").append(value);
		}
		sql.append(" ) ");
	}
	if(typeCode != null && typeCode.size() > 0){
		String typeSQL = String.join(", ", typeCode.stream().map(item -> "'" + item + "'").collect(Collectors.toList()));
		sql.append(" AND renttype.code IN ( " + typeSQL + " ) ");
	}
	sql.append(" GROUP BY b.id");
  }
}