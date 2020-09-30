package com.lti.repository;

public interface GenericRepository {

	<T> T save(Object obj);

	<T> T fetchById(Class<T> classname, Object id);

}