package service;

import model.Account;

public interface AccountService
{

	boolean createAccount(Account account);

	boolean loginAccount(Account account);

	boolean deposit(Account account, double amount);

	boolean withdraw(Account account, double amount);

	boolean transfer(Account from, String to, double amount);

	void showBalance(Account account);

	void showDetails(Account account);
}
