package com.javaweb.Repository.Custom.IMPL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.Repository.Custom.IRentAreaRepositoryCustomer;
import com.javaweb.Repository.Entity.RentAreaEntity;
import com.javaweb.Utils.ConnectionJDBC;

@Repository
public class RentAreaRepository implements IRentAreaRepositoryCustomer {

	@Override
	public List<RentAreaEntity> findByBuildingId(Long id) {
		try {
			List<RentAreaEntity> result = new ArrayList<RentAreaEntity>();
			StringBuilder sql = new StringBuilder("SELECT rentarea.id, rentarea.value FROM rentarea WHERE buildingid = ");
			sql.append(id);
			Connection connection = ConnectionJDBC.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				RentAreaEntity rentAreaEntity = new RentAreaEntity();
				rentAreaEntity.setId(resultSet.getLong("id"));
				rentAreaEntity.setValue(resultSet.getInt("value"));
				result.add(rentAreaEntity);
			}
			return result;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

}
