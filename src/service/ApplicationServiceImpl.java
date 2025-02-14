package service;

import model.Account;

import java.util.Scanner;

public class ApplicationServiceImpl implements ApplicationService {

    enum OperationType {
        WITHDRAW, DEPOSIT
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome Sir");

        boolean quit = false;
        int tails = 4;
        while (!quit && tails > 0) {
            System.out.println("Please Enter your choose");
            System.out.println("a.login     b.signup   c.exit");
            char choose = scanner.next().toLowerCase().charAt(0);
            switch (choose) {
                case 'a':
                    login();
                    break;
                case 'b':
                    signup();
                    break;
                case 'c':
                    quit = true;
                    System.out.println("you are welcome.");
                    break;
                default:
                    tails--;
                    System.out.println("Invalid Choose");
            }
            if (tails == 0 || quit) {
                break;
            }
        }
    }

    private void signup() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please Enter User name");
        String name = scanner.nextLine();

        System.out.println("Please Enter password");
        String password = scanner.nextLine();

        ValidationService validationService = new ValidationServiceImpl();

        if (!validationService.validateUserName(name)) { // "eslam"
            System.out.println("Invalid UserName");
            return;
        }

        if (!validationService.validatePassword(password)) {
            System.out.println("Invalid Password");
            return;
        }

        AccountService accountService = AccountServiceImpl.getInstance();
        Account account = new Account(name, password);
        boolean isAccountCreated = accountService.createAccount(account);
        if (isAccountCreated) {
            System.out.println("Account Created");
        } else {
            System.out.println("Account not Created Because There exist account with same user name");
        }

    }

    private void login() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please Enter User name");
        String name = scanner.nextLine();

        System.out.println("Please Enter password");
        String password = scanner.nextLine();

        ValidationService validationService = new ValidationServiceImpl();

        if (!validationService.validateUserName(name)) {
            System.out.println("Invalid UserName");
            return;
        }

        if (!validationService.validatePassword(password)) {
            System.out.println("Invalid Password");
            return;
        }

        AccountService accountService = AccountServiceImpl.getInstance();
        Account account = new Account(name, password);
        if (accountService.loginAccount(account)) {
            System.out.println("Login Success");
            services(account);
        } else {
            System.out.println("Account not Exist");
        }
    }

    private void services(Account account) {

        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        while (!quit) {
            System.out.println("1.Deposit   2.Withdraw    3.show details    4.Transfer    5. show balance   6.exit  7.logout");
            System.out.println("Enter your choice");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    deposit(account);
                    break;
                case 2:
                    withdraw(account);
                    break;
                case 3:
                    showDetails(account);
                    break;
                case 4:
                    transfer(account);
                    break;
                case 5:
                    showBalance(account);
                    break;
                case 6:
                    System.out.println("you are welcome.");
                    System.exit(0);
                    break;
                case 7:
                    quit = true;
                    System.out.println("You Logged out.");
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
            if (quit) {
                break;
            }
        }
    }

    void deposit(Account a) {
        operation(a, OperationType.DEPOSIT);
    }

    void withdraw(Account a) {
        operation(a, OperationType.WITHDRAW);
    }

    private static void operation(Account a, OperationType type) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Enter your %s amount:%n", type.name().toLowerCase());
        double amount = scanner.nextDouble();
        ValidationService validationService = new ValidationServiceImpl();
        AccountService accountService = AccountServiceImpl.getInstance();
        if (type == OperationType.DEPOSIT) {
            if (validationService.validateDeposit(amount)) {
                if (!accountService.deposit(a, amount)) {
                    System.out.println("Deposit Failed");
                } else {
                    System.out.println("Deposit Success");
                }
            } else {
                System.out.println("Amount must be greater than 100 and less than 20000");
            }
        } else {
            if (validationService.validateWithdraw(amount)) {
                if (!accountService.withdraw(a, amount)) {
                    System.out.println("Withdraw Failed");
                } else {
                    System.out.println("Withdraw Success");
                }
            } else {
                System.out.println("Amount must be greater than 100 and less than 8000");
            }
        }
    }

    void showDetails(Account a) {
        AccountService accountService = AccountServiceImpl.getInstance();
        accountService.showDetails(a);
    }

    void transfer(Account withdrawAccount) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username of account to transfer: ");
        String username = scanner.nextLine();

        System.out.println("Enter amount of money: ");
        double amount = Double.parseDouble(scanner.nextLine());

        ValidationService validationService = new ValidationServiceImpl();
        AccountService accountService = AccountServiceImpl.getInstance();

        if (validationService.validateTransfer(amount)) {
            if (accountService.transfer(withdrawAccount, username, amount)) {
                System.out.println("Transfer Success");
            } else {
                System.out.println("Transfer Failed");
            }
        } else {
            System.out.println("Amount of money must be greater than 0");
        }
    }

    void showBalance(Account a) {
        AccountService accountService = AccountServiceImpl.getInstance();
        accountService.showBalance(a);
    }
}
