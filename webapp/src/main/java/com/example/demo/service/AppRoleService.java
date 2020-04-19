package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.AppRole;
import com.example.demo.domain.AppRoleRepository;
import com.example.demo.domain.AppUser;
import com.example.demo.domain.AppUserRepository;

@Service
public class AppRoleService {

	@Autowired
	private AppRoleRepository appRoleRepository;
	
	@Autowired
	private AppUserRepository appUserRepository;
	
	public String seletcTest() {
		
		AppRole appRole = appRoleRepository.findOne(1L);
		System.out.println(appRole.getRoleName());
		
//		AppRole insertAppRole = new AppRole();
//		insertAppRole.setRoleId(3L);
//		insertAppRole.setRoleName("DB_ADMIN");
//		appRoleRepository.save(insertAppRole);
		
//		appRole.setRoleId(4L);
//		appRole.setRoleName("TEST_ADMIN");
		appRole.setRoleName("ADMIN");
		appRoleRepository.save(appRole);
		
		AppUser appUser = appUserRepository.findOne(1L);
		System.out.println(appUser.getUserName());
		System.out.println(appUser.getEncrytedPassword());
		
		return appRole.getRoleName();
	}
}
