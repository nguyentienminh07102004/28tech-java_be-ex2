package com.javaweb.Converter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.javaweb.Builder.BuildingSearchBuilder;
import com.javaweb.Utils.MapUtil;

@Component
public class BuildingSearchBuilderConvertor {
	public BuildingSearchBuilder toBuildingSearchBuilder(Map<String, String> params, List<String> typeCode) {
		return new BuildingSearchBuilder.Builder()
										.setName(MapUtil.getObject(params, "name", String.class))
										.setFloorArea(MapUtil.getObject(params, "floorArea", Integer.class))
										.setWard(MapUtil.getObject(params, "ward", String.class))
										.setStreet(MapUtil.getObject(params, "street", String.class))
										.setDistrictId(MapUtil.getObject(params, "districtId", Long.class))
										.setNumberOfBasement(MapUtil.getObject(params, "numberOfBasement", Integer.class))
										.setLevel(MapUtil.getObject(params, "level", String.class))
										.setTypeCode(typeCode)
										.setManagerName(MapUtil.getObject(params, "managerName", String.class))
										.setManagerPhoneNumber(MapUtil.getObject(params, "managerPhoneNumber", String.class))
										.setRentPriceFrom(MapUtil.getObject(params, "rentPriceFrom", Integer.class))
										.setRentPriceTo(MapUtil.getObject(params, "rentPriceTo", Integer.class))
										.setAreaFrom(MapUtil.getObject(params, "areaFrom", Integer.class))
										.setAreaTo(MapUtil.getObject(params, "areaTo", Integer.class))
										.setStaffId(MapUtil.getObject(params, "staffId", Long.class))
										.build();
	}
}
