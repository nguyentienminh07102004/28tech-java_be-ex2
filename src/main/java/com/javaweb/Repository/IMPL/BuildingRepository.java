package com.javaweb.Repository.IMPL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.javaweb.Repository.IBuildingRepository;
import com.javaweb.Repository.Entity.BuildingEntity;
import com.javaweb.Utils.validateDataInput;

@Repository
public class BuildingRepository implements IBuildingRepository {

  private String url = "jdbc:mysql://localhost:3306/estatebasic?allowPublicKeyRetrieval=true&useSSL=false";
  private String user = "root";
  private String password = "Minhnguyen12345!";

  private Connection getConnection() throws ClassNotFoundException, SQLException {
    Class.forName("com.mysql.cj.jdbc.Driver");
    return DriverManager.getConnection(url, user, password);
  }

  @Override
  public List<BuildingEntity> findAll(Map<String, String> params, List<String> typeCode)
      throws SQLException, ClassNotFoundException, NumberFormatException {
    StringBuilder sql = new StringBuilder("SELECT b.id, b.name, b.districtid, b.street, b.floorarea, b.ward, b.numberOfBasement, b.managerName, b.managerPhoneNumber, " +
											"b.rentprice, b.servicefee, b.brokeragefee FROM building b ");
	joinTable(params, typeCode, sql);
	where_condition(params, typeCode, sql);
	System.out.println(sql.toString());
    Connection connection = getConnection();
    PreparedStatement statement = connection.prepareStatement(sql.toString());
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
  }

  private void joinTable(Map<String, String> params, List<String> typeCode, StringBuilder sql) {
    if(typeCode != null && typeCode.size() > 0) {
		sql.append(" INNER JOIN buildingrenttype ON buildingrenttype.buildingId = b.id ");  
		sql.append(" INNER JOIN renttype ON renttype.id = buildingrenttype.renttypeId ");
	}
	if(validateDataInput.StringValidate(params.get("staffId"))) {
		sql.append(" INNER JOIN assignmentbuilding ON b.id = assignmentbuilding.buildingid ");
	}
	if(validateDataInput.StringValidate(params.get("areaFrom")) || validateDataInput.StringValidate(params.get("areaTo"))) {
		sql.append(" INNER JOIN rentarea ON b.id = rentarea.buildingid ");
	}
  }

  private void where_condition(Map<String, String> params, List<String> typeCode, StringBuilder sql) {
	sql.append(" WHERE 1 = 1 ");
	for(Map.Entry<String, String> param : params.entrySet()) {
		String key = param.getKey();
		if(validateDataInput.StringValidate(key)) {
			if(key.equals("numberOfBasement")) {
				Integer.parseInt(param.getValue());
				sql.append(" AND b.numberofbasement = " + param.getValue());
			}
			else if(key.startsWith("area")) {
				Integer.parseInt(param.getValue());
				if(key.endsWith("From")) {
					sql.append(" AND rentarea.value >= " + param.getValue());
				} else {
					sql.append(" AND rentarea.value <= " + param.getValue());
				}
			}
			else if(key.equals("staffId")) {
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
			else {
				sql.append(" AND " + key + " LIKE '%" + param.getValue() + "%'");
			}
		}
	}
	if(typeCode != null && typeCode.size() > 0){
		List<String> codes = new ArrayList<String>();
		for(String code : typeCode) {
			codes.add("'" + code + "'");
		}
		sql.append(" AND renttype.code IN ( " + String.join(", ", codes) + " ) ");
	}
	sql.append(" GROUP BY b.id");
  }
}