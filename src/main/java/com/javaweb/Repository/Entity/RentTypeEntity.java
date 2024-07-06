package com.javaweb.Repository.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.context.annotation.Lazy;

@Entity
@Table(name = "renttype")
public class RentTypeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "code")
	private String code;
	@Column(name = "value")
	private Integer value;
	@ManyToMany
	@Lazy
	@JoinTable(name = "buildingrenttype",
	joinColumns = @JoinColumn(name = "renttypeid", nullable = false, referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "buildingid", nullable = false, referencedColumnName = "id"))
	private List<BuildingEntity> buildingEntities;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public List<BuildingEntity> getBuildingEntities() {
		return buildingEntities;
	}
	public void setBuildingEntities(List<BuildingEntity> buildingEntities) {
		this.buildingEntities = buildingEntities;
	}
	
}
