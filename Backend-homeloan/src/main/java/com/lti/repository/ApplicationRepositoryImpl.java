package com.lti.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Account;
import com.lti.entity.Application;
import com.lti.entity.Document;
import com.lti.entity.Income;
import com.lti.entity.Property;

@Repository
public class ApplicationRepositoryImpl implements ApplicationRepository {

	@Autowired
	private GenericRepository genericRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public int registerApplication(Application application) {
		application = genericRepository.save(application);
		return application.getApplicationId();
	}
	
	
	@Override
	public boolean isApplicationExists(String email) {
		return (Long) entityManager
				.createQuery("select count(a.applicationId) from Application a where a.email=:email")
				.setParameter("email", email)
				.getSingleResult() == 1 ? true : false;
	}
	
	
	@Override
	public int getApplicationId(String email, String password) {
		return (Integer) entityManager
				.createQuery("select a.applicationId from Application a where a.email =:email and a.password =:password")
				.setParameter("email", email)
				.setParameter("password", password)
				.getSingleResult();
	}
	
	
	@Override
	public String applicationStatus(int appNo) {
		return (String) entityManager
				.createQuery("select a.applicationStatus from Application a where a.applicationId =: appId")
				.setParameter("appId", appNo)
				.getSingleResult();
	}
	
	
	@Override
	public Application findById(int id) {
		return genericRepository.fetchById(Application.class, id);
	}
	
	
	@Override
	public Account displayAccountDetails(int accountNo) {
//		return (Account) entityManager
//				.createQuery("select a from Account a where a.accountNo =: accountNo")
//				.setParameter("accountNo", accountNo)
//				.getSingleResult();
		return genericRepository.fetchById(Account.class, accountNo);
	}
	
	
	@Override
	public boolean isAadharPresent(String aadharNo) {
		return (Long) entityManager
				.createQuery("select count(a.applicationId) from Application a where a.aadharNo = :aadharNo")
				.setParameter("aadharNo", aadharNo)
				.getSingleResult() < 1 ? false : true;
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Application> getApplicationListByAadhar(String aadharNo) {
		String jpql = "select  a from Application a where a.aadharNo =:aadharNo";
		Query q = entityManager.createQuery(jpql);
		q.setParameter("aadharNo", aadharNo);
		List<Application> applicationList = q.getResultList();
		return applicationList;
	}
	
	
	@Override
	public Application updateApplication(Application application) {
		Application updatedObj = genericRepository.save(application);
		return updatedObj;
	}
	
	
	@Override
	public int registerIncome(Income income) {
		income = genericRepository.save(income);
		return income.getIncomeId();
	}
	
	
	@Override
	public int registerProperty(Property property) {
		property = genericRepository.save(property);
		return property.getPropertyId();
	}
	
	
	@Override
	public int registerDocuments(Document document) {
		document = genericRepository.save(document);
		return document.getDocumentId();
	}
	
	@Override
	public Account findAccountByAccountNo(int accno) {
		Account account = genericRepository.fetchById(Account.class, accno);
		return account;
		
	}


	@Override
	public Application findApplicationById(int appid) {
		Application application = genericRepository.fetchById(Application.class, appid);
		return application;
	}
	
	
	@Override
	public Account findAccountByAppId(int appId) {
		return (Account) entityManager
				.createQuery("select a from Account a where a.application.applicationId = :appId")
				.setParameter("appId", appId)
				.getSingleResult();
	}
	
	
	@Override
	public boolean isAccountPresent(int appId) {
		return (Long) entityManager
				.createQuery("select count(a.accountNo) from Account a where a.application.applicationId = :appId")
				.setParameter("appId", appId)
				.getSingleResult() < 1 ? false : true;
	}
	
	
	@Override
	public boolean isIncomeFormFilled(int applicationId) {
		return (Long) entityManager
				.createQuery("select count(i.incomeId) from Income i where i.application.applicationId = :appId")
				.setParameter("appId", applicationId)
				.getSingleResult() < 1 ? false : true;
	}
	
	
	@Override
	public boolean isPropertyFormFilled(int applicationId) {
		return (Long) entityManager
				.createQuery("select count(p.propertyId) from Property p where p.application.applicationId = :appId")
				.setParameter("appId", applicationId)
				.getSingleResult() < 1 ? false : true;
	}
	
	
	@Override
	public boolean isLoanFormFilled(int applicationId) {
		return (Long) entityManager
				.createQuery("select count(l.loanId) from Loan l where l.application.applicationId = :appId")
				.setParameter("appId", applicationId)
				.getSingleResult() < 1 ? false : true;
	}
	
	
	@Override
	public boolean isDocumentFormFilled(int applicationId) {
		return (Long) entityManager
				.createQuery("select count(d.documentId) from Document d where d.application.applicationId = :appId")
				.setParameter("appId", applicationId)
				.getSingleResult() < 1 ? false : true;
	}
}
