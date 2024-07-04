package com.javaweb.Repository.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rentarea")
public class RentAreaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "value")
	private Integer value;
	@ManyToOne
	@JoinColumn(name = "buildingid")
	private BuildingEntity buildingId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public BuildingEntity getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(BuildingEntity buildingId) {
		this.buildingId = buildingId;
	}

}
