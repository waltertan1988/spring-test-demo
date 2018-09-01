package com.my.demo.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="t_user")
public class User implements Persistable<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "uid", unique = true, nullable = false)
	private long id;
	
	@Column(length=255,unique=true, nullable=false, name="username")
	private String username;
	
	@Column(length=255, name="user_real_name")
	private String userRealName;
	
	@Column(length=255, nullable=false, name="password")
	private String password;
	
	@Column(length=1, nullable=false, name="gender")
	private String gender;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "birthday")
	private Date birthday;
	
	public void setId(long id) {
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	@Override
	public Long getId() {
		return id;
	}
	
	@JsonIgnore
	@Override
	public boolean isNew() {
		return false;
	}
}
