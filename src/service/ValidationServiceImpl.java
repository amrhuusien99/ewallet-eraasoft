package service;

public class ValidationServiceImpl implements ValidationService {

    @Override
    public boolean validateUserName(String userName) {
        if (userName.length() < 3) {
            return false;
        }
        char firstLetter = userName.charAt(0);
        return Character.isUpperCase(firstLetter);
    }

    @Override
    public boolean validatePassword(String password) {

        if (password.length() < 6) {
            return false;
        }

        boolean hasDigit = false, hasUpper = false, hasLower = false, hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {

            char c = password.charAt(i);
            if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isLowerCase(c)) {
                hasLower = true;
            } else {
                hasSpecial = true;
            }

            if (hasDigit && hasUpper && hasLower && hasSpecial) {
                return true;
            }

        }

        return false;
    }

    @Override
    public boolean validateDeposit(double amount) {
        return amount >= 100 && amount <= 20000;
    }

    @Override
    public boolean validateWithdraw(double amount) {
        return amount >= 100 && amount <= 8000;
    }

    @Override
    public boolean validateTransfer(double amount) {
        return amount > 0;
    }

}
