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
@Table(name = "user")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "fullname")
	private String fullname;
	@Column(name = "phone")
	private String phone;
	@Column(name = "email")
	private String email;
	@Column(name = "status", nullable = false)
	private Integer status;

	// @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
	// private List<UserRoleEntity> userRoleEntities;

	@ManyToMany
	@Lazy
	@JoinTable(name = "user_role",
			joinColumns = @JoinColumn(name = "userid", nullable = false, referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "roleid", nullable = false, referencedColumnName = "id"))
	private List<RoleEntity> roleEntities;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public List<RoleEntity> getRoleEntities() {
		return roleEntities;
	}
	public void setRoleEntities(List<RoleEntity> roleEntities) {
		this.roleEntities = roleEntities;
	}
}
