package com.javaweb.Repository.IMPL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.Repository.IRentAreaRepository;
import com.javaweb.Repository.Entity.RentAreaEntity;

@Repository
public class RentAreaRepository implements IRentAreaRepository {

	private String url = "jdbc:mysql://localhost:3306/estatebasic?allowPublicKeyRetrieval=true&useSSL=false";
    private String username = "root";
    private String password = "Minhnguyen12345!";

	@Override
	public List<RentAreaEntity> findByBuildingId(Long id) throws SQLException, ClassNotFoundException {
		List<RentAreaEntity> result = new ArrayList<>();
		StringBuilder sql = new StringBuilder("SELECT rentarea.id, rentarea.value FROM rentarea WHERE buildingid = ");
		sql.append(id);
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection(url, username, password);
		PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) {
			RentAreaEntity rentAreaEntity = new RentAreaEntity();
			rentAreaEntity.setId(resultSet.getLong("id"));
			rentAreaEntity.setValue(resultSet.getInt("value"));
			result.add(rentAreaEntity);
		}
		return result;
	}

}
