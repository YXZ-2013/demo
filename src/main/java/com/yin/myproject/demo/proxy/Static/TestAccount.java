package com.yin.myproject.demo.proxy.Static;

public class TestAccount {
	public static void main(String[] args) {
		AccountImpl accountImpl = new AccountImpl();
		AccountProxy accountProxy = new AccountProxy(accountImpl);
		accountProxy.queryAccount();
		accountProxy.updateAccount();
	}
}
