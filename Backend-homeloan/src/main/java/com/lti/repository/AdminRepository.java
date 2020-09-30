package com.lti.repository;

import com.lti.entity.Admin;

public interface AdminRepository {

	int registerAdmin(Admin admin);
	
	Admin findById(int id);

	boolean isAdmin(String username);

	int getAdminId(String username, String password);

}