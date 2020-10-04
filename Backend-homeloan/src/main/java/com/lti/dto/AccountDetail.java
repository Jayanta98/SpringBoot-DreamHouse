package com.lti.dto;

import com.lti.entity.Account;

public class AccountDetail {
	
	Account account;
	boolean accountStatus;
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public boolean isAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(boolean accountStatus) {
		this.accountStatus = accountStatus;
	}
	
	

}
