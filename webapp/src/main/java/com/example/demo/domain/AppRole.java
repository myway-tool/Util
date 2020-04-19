package com.example.demo.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="app_role")
public class AppRole implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="role_id")
	private Long roleId;
	
	@Column(name="role_name")
	private String roleName;	
	
}
