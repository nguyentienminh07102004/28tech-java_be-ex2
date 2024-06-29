package com.javaweb.Repository.IMPL;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.javaweb.Builder.BuildingSearchBuilder;
import com.javaweb.Repository.IBuildingRepository;
import com.javaweb.Repository.Entity.BuildingEntity;
import com.javaweb.Utils.ConnectionJDBC;
import com.javaweb.Utils.validateDataInput;

@Repository
public class BuildingRepository implements IBuildingRepository {

  @Override
  public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
	try {
		StringBuilder sql = new StringBuilder("SELECT b.id, b.name, b.districtid, b.street, b.floorarea, b.ward, b.numberOfBasement, b.managerName, b.managerPhoneNumber, ");
		sql.append("b.rentprice, b.servicefee, b.brokeragefee FROM building b ");
		joinTable(buildingSearchBuilder, sql);
		where_condition_normal(buildingSearchBuilder, sql);
		where_condition_special(sql, buildingSearchBuilder);
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

  private void joinTable(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {
    if(buildingSearchBuilder.getTypeCode() != null && !buildingSearchBuilder.getTypeCode().isEmpty()) {
		sql.append(" INNER JOIN buildingrenttype ON buildingrenttype.buildingId = b.id ");
		sql.append(" INNER JOIN renttype ON renttype.id = buildingrenttype.renttypeId ");
	}
	if(buildingSearchBuilder.getStaffId() != null) {
		sql.append(" INNER JOIN assignmentbuilding ON b.id = assignmentbuilding.buildingid ");
	}
  }

  private void where_condition_normal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {
	sql.append(" WHERE 1 = 1 ");
	try{
		Field[] feilds = BuildingSearchBuilder.class.getDeclaredFields();
		for(Field feild : feilds) {
			feild.setAccessible(true);
			String key = feild.getName().trim();
			if(!key.toLowerCase().startsWith("area") && !key.equalsIgnoreCase("typeCode")) {
				String value = feild.get(buildingSearchBuilder) == null ? null : feild.get(buildingSearchBuilder).toString();
				if(validateDataInput.StringValidate(value)) {
					if(key.equalsIgnoreCase("numberOfBasement")) {
						Integer numberOfBasement = Integer.parseInt(value);
						sql.append(" AND b.numberofbasement = ").append(numberOfBasement);
					}
					else if(key.equalsIgnoreCase("staffId")) {
						Long staffId = Long.parseLong(value);
						sql.append(" AND assignmentbuilding.staffid = ").append(staffId);
					}
					else if(key.startsWith("rentPrice")) {
						Integer rentPrice = Integer.parseInt(value);
						if(key.endsWith("From")) {
							sql.append(" AND b.rentPrice >= ").append(rentPrice);
						} else {
							sql.append(" AND b.rentPrice <= ").append(rentPrice);
						}
					}
					else if(!key.equalsIgnoreCase("typeCode")){
						sql.append(" AND b.").append(key).append(" LIKE '%").append(value).append("%'");
					}
				}
			}
		}
	} catch (Exception ex) {
		System.out.println(ex.getMessage());
	}
  }

  private void where_condition_special(StringBuilder sql, BuildingSearchBuilder buildingSearchBuilder) {
	Integer areaFrom = buildingSearchBuilder.getAreaFrom();
	Integer areaTo = buildingSearchBuilder.getAreaTo();
	if(areaFrom != null || areaTo != null) {
		sql.append(" AND EXISTS (SELECT rentarea.value FROM rentarea WHERE rentarea.buildingid = b.id ");
		if(areaFrom != null) {
			sql.append(" AND rentarea.value >= ").append(areaFrom);
		}
		if(areaTo != null) {
			sql.append(" AND rentarea.value <= ").append(areaTo);
		}
		sql.append(" ) ");
	}
	List<String> typeCode = buildingSearchBuilder.getTypeCode();
	if(typeCode != null && typeCode.size() > 0){
		String typeSQL = String.join(", ", typeCode.stream().map(item -> "'" + item + "'").collect(Collectors.toList()));
		sql.append(" AND renttype.code IN ( " + typeSQL + " ) ");
	}
	sql.append(" GROUP BY b.id");
  }
}