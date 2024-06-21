package com.javaweb.Repository.IMPL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.Repository.IBuildingRepository;
import com.javaweb.Repository.Entity.BuildingEntity;

@Repository
public class BuildingRepository implements IBuildingRepository {

  private String url = "jdbc:mysql://localhost:3306/estatebasic?allowPublicKeyRetrieval=true&useSSL=false";
  private String user = "root";
  private String password = "Minhnguyen12345!";

  @Override
  public List<BuildingEntity> findAll(String name, String floorArea, String district, String ward, String street, Integer numberOfBasement, String direction, String level, String rentFrom,
  String rentTo, String rentPriceFrom, String rentPriceTo, String managerName, String managerPhoneNumber, String staffId, List<String> typeCode) {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    try {
      List<BuildingEntity> results = new ArrayList<BuildingEntity>();
      StringBuilder sql = new StringBuilder("SELECT b.name, b.ward, b.street, district.name, b.numberOfbasement, b.managername, b.managerphonenumber, b.floorarea, rentarea.value, b.servicefee, b.deposit\n" + //
                "FROM building b\n" + //
                "INNER JOIN buildingrenttype ON buildingrenttype.buildingId = b.id\n" + //
                "INNER JOIN renttype ON renttype.id = buildingrenttype.renttypeId\n" + //
                "INNER JOIN rentarea ON rentarea.buildingid = b.id\n" + //
                "INNER JOIN district ON b.districtId = district.id\n" + //
                "INNER JOIN assignmentbuilding ON assignmentbuilding.buildingId = b.id\n" + //
                "WHERE b.name LIKE ? AND b.street LIKE ? AND b.ward LIKE ? AND b.floorArea = 500 AND b.direction LIKE ? AND b.numberOfBasement = ?\n" + //
                "AND b.level LIKE ? AND b.districtId = ? AND b.rentPrice >= 10 AND b.rentPrice <= 19 AND b.managerName LIKE ? AND b.managerPhoneNumber LIKE ?\n" + //
                "AND assignmentbuilding.staffId = 2\n" + //
                "GROUP BY b.id");
      
      Class.forName("com.mysql.cj.jdbc.Driver");
      connection = DriverManager.getConnection(url, user, password);
      statement = connection.prepareStatement(sql.toString());
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

}
