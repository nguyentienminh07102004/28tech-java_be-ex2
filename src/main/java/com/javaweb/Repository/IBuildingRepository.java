package com.javaweb.Repository;

import java.util.List;

import com.javaweb.Builder.BuildingSearchBuilder;
import com.javaweb.Repository.Entity.BuildingEntity;

public interface IBuildingRepository {
  List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder);
}
