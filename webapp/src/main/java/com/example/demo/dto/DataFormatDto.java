package com.example.demo.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class DataFormatDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
}
