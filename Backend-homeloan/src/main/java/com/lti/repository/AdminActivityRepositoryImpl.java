package com.lti.repository;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lti.entity.Account;
import com.lti.entity.Application;
import com.lti.entity.Document;
import com.lti.entity.Income;
import com.lti.entity.Loan;
import com.lti.entity.Property;

@Repository
public class AdminActivityRepositoryImpl implements AdminActivityRepository {

	@Autowired
	private GenericRepository genericRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	//Inserting Account Details For Particular Application By Admin
	@Override
	public void insertAccountDetailsByAdmin(Account account) {
		genericRepository.save(account);
	}
	
	
	//See Account details by accountNo by Admin
	@Override
	public Account displayAccountDetailsByAdmin(int accountNo) {
		return genericRepository.fetchById(Account.class, accountNo);
	}
	
	
	//Full Account Table Seen by Admin
	@Override
	@SuppressWarnings("unchecked")
	public List<Account> displayFullAccountTableByAdmin() {
		String jpql = "select a from Account a";
		Query q = entityManager.createQuery(jpql);
		List<Account> listofAccount = q.getResultList();
		return listofAccount;
	}
	
	
	//Status Update by admin from pending to <some value>
	@Override
	public void updateApplicationStatus(int appNo, String newStatus) {
		entityManager
		.createQuery("update Application a set a.applicationStatus =: newStatus where a.applicationId = :appNo")
		.setParameter("newStatus", newStatus)
		.setParameter("appNo", appNo)
		.executeUpdate();
	}

	
	//Update Loan Status by Admin
	@Override
	public void updateLoanStatusByAdmin(int appNo, String loanStatus) {
		entityManager
		.createQuery("update Loan l set l.loanStatus = :loanStatus where l.application.applicationId = :appNo")
		.setParameter("loanStatus", loanStatus)
		.setParameter("appNo", appNo)
		.executeUpdate();
	}
	
	
	//Update Loan Table By Admin
	//Update LoanStatus By Admin
	
	@Override
	public void updateLoanDetailsByAdmin(int appNo, double emi, LocalDate startDate, LocalDate endDate) {
		entityManager
		.createQuery("update Loan l set l.emi =: emi,l.startDate=:startDate,l.endDate=:endDate  where l.application.applicationId=:appNo")
		.setParameter("emi", emi)
		.setParameter("startDate", startDate)
		.setParameter("endDate", endDate)
		.setParameter("appNo", appNo)
		.executeUpdate();
	}
	
	
	//Get all applications list
	@Override
	@SuppressWarnings("unchecked")
	public List<Application> getApplicants() {
		String jpql = "select a from Application a";
		Query q = entityManager.createQuery(jpql);
		List<Application> list = q.getResultList();
		return list;
	}
	
	
	//Fetch single application detail by Id
	@Override
	public Application applicationDetailById(int applicationId) {
//		return (Application) entityManager
//				.createQuery("select a from Application a where a.applicationId =:appId")
//				.setParameter("appId", appId)
//				.getSingleResult();
		return genericRepository.fetchById(Application.class, applicationId);
	}
	
	
	//Get all properties list
	@Override
	@SuppressWarnings("unchecked")
	public List<Property> getProperty() {
		String jpql = "select p from Property p";
		Query q = entityManager.createQuery(jpql);
		List<Property> list = q.getResultList();
		return list;
	}
	
	
	//Fetch single property detail by ApplicationId
	@Override
	public Property propertyDetailByApplicationId(int appId) {
		return (Property) entityManager
				.createQuery("select p from Property p where p.application.applicationId =:appId")
				.setParameter("appId", appId)
				.getSingleResult();
	}
	
	
	//Get all incomes list
	@Override
	@SuppressWarnings("unchecked")
	public List<Income> getAllIncomeDetails() {
		String jpql = "select i from Income i";
		Query q = entityManager.createQuery(jpql);
		List<Income> list = q.getResultList();
		return list;
	}
	
	
	//Fetch income detail by ApplicationId
	@Override
	public Income incomeDetailsByApplicationId(int appId) {
		return (Income) entityManager
				.createQuery("select i from Income i where i.application.applicationId = :appId")
				.setParameter("appId", appId)
				.getSingleResult();
	}
	
	
	//Get all loans list
	@Override
	@SuppressWarnings("unchecked")
	public List<Loan> getAllLoanDetails() {
		String jpql = "select l from Loan l";
		Query q = entityManager.createQuery(jpql);
		List<Loan> list = q.getResultList();
		return list;
	}
	
	
	//Fetch loan detail by ApplicationId
	@Override
	public Loan loanDetailsByApplicationId(int appId) {
		return (Loan) entityManager
				.createQuery("select l from Loan l where l.application.applicationId = :appId")
				.setParameter("appId", appId)
				.getSingleResult();
	}
	
	
	//Get all documents list
	@Override
	@SuppressWarnings("unchecked")
	public List<Document> getAllDocumentDetails() {
		String jpql = "select d from Document d";
		Query q = entityManager.createQuery(jpql);
		List<Document> list = q.getResultList();
		return list;
	}
	
	
	//Fetch document detail by ApplicationId
	@Override
	public Document documentDetailsByApplicationId(int appId) {
		return (Document) entityManager
				.createQuery("select d from Document d where d.application.applicationId = :appId")
				.setParameter("appId", appId)
				.getSingleResult();
	}
	
	
	//Update application
	@Override
	public Application updateApplicationByAdmin(Application application) {
		Application updatedObj = genericRepository.save(application);
		return updatedObj;
	}
	
	
	//Update account
		@Override
		public Account updateAccountByAdmin(Account account) {
			Account updatedObj = genericRepository.save(account);
			return updatedObj;
		}
	
}
