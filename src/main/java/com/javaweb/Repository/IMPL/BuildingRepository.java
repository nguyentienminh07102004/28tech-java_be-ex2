package com.javaweb.Repository.IMPL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.javaweb.Repository.IBuildingRepository;
import com.javaweb.Repository.Entity.BuildingEntity;

@Repository
public class BuildingRepository implements IBuildingRepository {

  private String url = "jdbc:mysql://localhost:3306/estatebasic?allowPublicKeyRetrieval=true&useSSL=false";
  private String user = "root";
  private String password = "Minhnguyen12345!";

  @Override
  public List<BuildingEntity> findAll(Map<String, String> params, List<String> typeCode) {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    try {
      List<BuildingEntity> results = new ArrayList<BuildingEntity>();
      StringBuilder sql = new StringBuilder("SELECT b.name, b.ward, b.street, district.name AS district, b.numberOfbasement, b.managername, b.managerphonenumber, b.floorarea, GROUP_CONCAT(rentarea.value SEPARATOR ',') AS rentarea , b.servicefee, b.brokeragefee, b.rentprice ");
      Class.forName("com.mysql.cj.jdbc.Driver");
      connection = DriverManager.getConnection(url, user, password);
      String SQL = sqlString(sql, params, typeCode);
      statement = connection.prepareStatement(SQL);
      resultSet = statement.executeQuery();
      while (resultSet.next()) {
        BuildingEntity building = new BuildingEntity();
        building.setName(resultSet.getString("name"));
        building.setNumberOfBasement(resultSet.getInt("numberofbasement"));
        building.setStreet(resultSet.getString("street"));
        building.setWard(resultSet.getString("ward"));
        building.setDistrict(resultSet.getString("district"));
        building.setManagerName(resultSet.getString("managername"));
        building.setManagerPhoneNumber(resultSet.getString("managerphonenumber"));
        building.setFloorArea(resultSet.getInt("floorarea"));
        building.setRentPrice(resultSet.getInt("rentprice"));
        building.setServiceFee(resultSet.getString("servicefee"));
        building.setBrokerageFee(resultSet.getString("brokeragefee"));
        building.setRentArea(resultSet.getString("rentarea"));
        results.add(building);
      }
      return results;
    } catch (Exception e) {
      System.out.println("Fail");
      return null;
    } finally {
      try {
        if(connection != null) connection.close();
        if(statement != null) statement.close();
        if(resultSet != null) resultSet.close();
      } catch (Exception e) {

      }
    }
  }

  private String sqlString (StringBuilder sql, Map<String, String> params, List<String> typeCode) {
    sql.append(" FROM building b ");
    sql.append(" INNER JOIN rentarea ON rentarea.buildingid = b.id ");
    sql.append(" INNER JOIN district ON b.districtid = district.id ");
    String district = params.get("district");
    String floorArea = params.get("floorArea");
    String name = params.get("name");
    String ward = params.get("ward");
    String street = params.get("street");
    String numberOfBasement = params.get("numberOfBasement");
    String direction = params.get("direction");
    String level = params.get("level");
    String areaFrom = params.get("areaFrom");
    String areaTo = params.get("areaTo");
    String rentPriceFrom = params.get("rentPriceFrom");
    String rentPriceTo = params.get("rentPriceTo");
    String managerName = params.get("managerName");
    String managerPhoneNumber = params.get("managerPhoneNumber");
    String staffId = params.get("staffId");

    if(staffId != null && !staffId.equals("")) {
      sql.append(" INNER JOIN assignmentbuilding ON assignmentbuilding.buildingId = b.id ");
    }
    if(typeCode != null) {
      sql.append(" INNER JOIN buildingrenttype ON buildingrenttype.buildingId = b.id ");
      sql.append(" INNER JOIN renttype ON renttype.id = buildingrenttype.renttypeId ");
    }

    sql.append(" WHERE 1 = 1 ");

    if(name != null && name.equals("") == false) {
      sql.append(" AND b.name LIKE '%" + name + "%'");
    }
    if(floorArea != null && floorArea.equals("") == false) {
      sql.append(" AND b.floorarea = " + floorArea);
    }
    if(district != null && district.equals("") == false) {
      sql.append(" AND b.districtid = " + district);
    }
    if(ward != null && ward.equals("") == false) {
      sql.append(" AND b.ward LIKE '%" + ward + "%'");
    }
    if(street != null && street.equals("") == false) {
      sql.append(" AND b.street LIKE '%" + street + "%'");
    }
    if(numberOfBasement != null && numberOfBasement.equals("") == false) {
      Integer.parseInt(numberOfBasement);
      sql.append(" AND b.numberofbasement = " + numberOfBasement);
    }
    if(direction != null && !direction.equals("")) {
      sql.append(" AND b.direction LIKE '%" + direction + "%'");
    }
    if(level != null && !level.equals("")) {
      sql.append(" AND b.level LIKE '%" + level + "%'");
    }
    if(rentPriceFrom != null && !rentPriceFrom.equals("")) {
      Integer.parseInt(rentPriceFrom);
      sql.append(" AND b.rentprice >= " + rentPriceFrom);
    }
    if(rentPriceTo != null && !rentPriceTo.equals("")) {
      Integer.parseInt(rentPriceTo);
      sql.append(" AND b.rentprice <= " + rentPriceTo);
    }
    if(managerName != null && !managerName.equals("")) {
      sql.append(" AND b.managername LIKE '%" + managerName + "%'");
    }
    if(managerPhoneNumber != null && !managerPhoneNumber.equals("")) {
      sql.append(" AND b.managerphonenumber LIKE '%" + managerPhoneNumber + "%'");
    }
    if(staffId != null && !staffId.equals("")) {
      Integer.parseInt(staffId);
      sql.append(" AND assignmentbuilding.staffid = " + staffId);
    }
    if(typeCode != null) {
      sql.append(" AND ( renttype.code = '" + typeCode.get(0) + "'");
      for(int i = 1; i < typeCode.size(); i++) {
        sql.append(" OR renttype.code = '" + typeCode.get(i) + "'");
      }
      sql.append(" ) ");
    }
    if(areaFrom != null && !areaFrom.equals("")) {
      Integer.parseInt(areaFrom);
      sql.append(" AND rentarea.value >= " + areaFrom);
    }
    if(areaTo != null && !areaTo.equals("")) {
      Integer.parseInt(areaTo);
      sql.append(" AND rentarea.value <= " + areaTo);
    }
    sql.append(" GROUP BY b.id");
    System.out.println(sql.toString());
    return sql.toString();
  }
}
