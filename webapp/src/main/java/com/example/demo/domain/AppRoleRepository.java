package com.example.demo.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRoleRepository extends CrudRepository<AppRole, Long> {
//public interface AppRoleRepository extends JpaRepository<AppRole, Long> {

//	List<AppRole> findByLastName(String lastName);

//	AppRole findById(long roleId);
}
