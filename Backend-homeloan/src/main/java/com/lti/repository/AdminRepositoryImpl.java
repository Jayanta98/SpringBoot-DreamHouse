package com.lti.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lti.entity.Admin;

@Repository
public class AdminRepositoryImpl implements AdminRepository {

	@Autowired
	private GenericRepository genericRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public int registerAdmin(Admin admin) {
		admin = genericRepository.save(admin);
		return admin.getId();
	}
	
	
	@Override
	public Admin findById(int id) {
		return genericRepository.fetchById(Admin.class, id);
	}
	
	
	@Override
	public boolean isAdmin(String username) {
		return (Long) entityManager
				.createQuery("select count(a.id) from Admin a where a.username = :username")
				.setParameter("username", username)
				.getSingleResult() == 1 ? true : false;
	}
	
	
	@Override
	public int getAdminId(String username, String password) {
		return (Integer) entityManager
				.createQuery("select a.id from Admin a where a.username =:username and a.password =:password")
				.setParameter("username", username)
				.setParameter("password", password)
				.getSingleResult();
	}
	
	
	
}
