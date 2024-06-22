package com.javaweb.Repository;

import java.util.List;
import java.util.Map;

import com.javaweb.Repository.Entity.BuildingEntity;

public interface IBuildingRepository {
  List<BuildingEntity> findAll(Map<String, String> params, List<String> typeCode);
}
