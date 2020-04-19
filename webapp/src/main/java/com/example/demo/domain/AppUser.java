package com.example.demo.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
//@Table(name="app_role")
public class AppUser implements Serializable{

	private static final long serialVersionUID = 1L;

//	user_id
//	user_name
//	encryted_password
//	enabled
	
	
	@Id
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="user_name")
	private String userName;	
	
	@Column(name="encryted_password")
	private String encrytedPassword;	
	
	@Column(name="enabled")
	private Integer enabled;	
}
