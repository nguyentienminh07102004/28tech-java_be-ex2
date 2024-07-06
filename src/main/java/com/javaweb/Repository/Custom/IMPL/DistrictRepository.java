package com.javaweb.Repository.Custom.IMPL;

import org.springframework.stereotype.Repository;

import com.javaweb.Repository.Custom.IDistrictRepositoryCustomer;

@Repository
public class DistrictRepository implements IDistrictRepositoryCustomer {

    // @Override
    // public DistrictEntity findById(Long id) {
    //     try {
    //         StringBuilder sql = new StringBuilder("SELECT * FROM district WHERE id = ");
    //         sql.append(id);
    //         Connection connection = ConnectionJDBC.getConnection();
    //         PreparedStatement statement =connection.prepareStatement(sql.toString());
    //         ResultSet resultSet = statement.executeQuery();
    //         DistrictEntity districtEntity = new DistrictEntity();
    //         if(resultSet.next()) {
    //             districtEntity.setId(resultSet.getLong("id"));
    //             districtEntity.setName(resultSet.getString("name"));
    //             districtEntity.setCode(resultSet.getString("code"));
    //         }
    //         return districtEntity;
    //     } catch (SQLException e) {
    //         return null;
    //     }
        
    // }

}
