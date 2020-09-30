package com.lti.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class GenericRepositoryImpl implements GenericRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@SuppressWarnings("unchecked")
	public <T> T save(Object obj) {
		return (T) entityManager.merge(obj);
	}
	
	@Override
	public <T> T fetchById(Class<T> classname, Object id) {
		return entityManager.find(classname, id);
	}
	
}
