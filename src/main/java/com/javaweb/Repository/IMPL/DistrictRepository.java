package com.javaweb.Repository.IMPL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.javaweb.Repository.IDistrictRepository;
import com.javaweb.Repository.Entity.DistrictEntity;

@Repository
public class DistrictRepository implements IDistrictRepository {

    private String url = "jdbc:mysql://localhost:3306/estatebasic?allowPublicKeyRetrieval=true&useSSL=false";
    private String username = "root";
    private String password = "Minhnguyen12345!";

    @Override
    public DistrictEntity findById(Long id) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM district WHERE id = " + id;
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);
        PreparedStatement statement =connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        DistrictEntity districtEntity = new DistrictEntity();
        if(resultSet.next()) {
            districtEntity.setId(resultSet.getLong("id"));
            districtEntity.setName(resultSet.getString("name"));
            districtEntity.setCode(resultSet.getString("code"));
        }
        return districtEntity;
    }

}
