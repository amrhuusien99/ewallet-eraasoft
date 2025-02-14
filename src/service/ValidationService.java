package service;

public interface ValidationService
{

	boolean validateUserName(String userName);

	boolean validatePassword(String password);

	boolean validateDeposit(double mo);

	boolean validateWithdraw(double amount);

	boolean validateTransfer(double amount);
}
