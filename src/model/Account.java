package model;

import java.util.Objects;

public class Account {

    private String userName;
    private String password;
    private double balance;
    private boolean active;

    public Account() {
    }

    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.balance = 0;
        this.active = true;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(this.getUserName(), account.getUserName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getUserName());
    }

    @Override
    public String toString() {
        return String.format("username: %10s, balance: %.2f, active: %b%n", userName, balance, active);
    }
}
