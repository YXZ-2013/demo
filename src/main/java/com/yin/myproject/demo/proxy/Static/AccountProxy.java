package com.yin.myproject.demo.proxy.Static;

public class AccountProxy implements Account{

	private AccountImpl accountImpl;
	
	public AccountProxy(AccountImpl accountImpl) {
		this.accountImpl = accountImpl;
	}

	public void queryAccount() {
		System.out.println("查询账户以前");
		accountImpl.queryAccount();
		System.out.println("查询账户以后");
	}

	public void updateAccount() {
		System.out.println("修改账户以前");
		accountImpl.queryAccount();
		System.out.println("修改账户以前");
	}
	
}
