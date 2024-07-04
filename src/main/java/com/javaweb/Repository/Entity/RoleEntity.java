package com.javaweb.Repository.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class RoleEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "code", nullable = false, unique = true)
	private String code;

	// @OneToMany(mappedBy = "roleId", fetch = FetchType.LAZY)
	// private List<UserRoleEntity> userRoleEntities;
	@ManyToMany(mappedBy = "roleEntities", fetch = FetchType.LAZY)
	private List<UserEntity> userEntities;	


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	// public List<UserRoleEntity> getUserRoleEntities() {
	// 	return userRoleEntities;
	// }
	// public void setUserRoleEntities(List<UserRoleEntity> userRoleEntities) {
	// 	this.userRoleEntities = userRoleEntities;
	// }
	public List<UserEntity> getUserEntities() {
		return userEntities;
	}
	public void setUserEntities(List<UserEntity> userEntities) {
		this.userEntities = userEntities;
	}
	
}
