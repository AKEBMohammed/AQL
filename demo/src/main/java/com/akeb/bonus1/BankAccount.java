package com.akeb.bonus1;

public class BankAccount {
    private double balance;
    private double interestRate;

    public BankAccount(double balance, double interestRate) {
        if (balance < 0) {
            throw new IllegalArgumentException("Initial balance must be non-negative");
        }
        if (interestRate < 0) {
            throw new IllegalArgumentException("Interest rate must be non-negative");
        }
        this.balance = balance;
        this.interestRate = interestRate;
    }

    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (amount > balance) {
            throw new IllegalStateException("Insufficient balance");
        }
        balance -= amount;
    }

    public void transfer(double amount, BankAccount other) {
        if (other == null) {
            throw new NullPointerException("Other account must not be null");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (amount > balance) {
            throw new IllegalStateException("Insufficient balance");
        }
        // Fix transactional issue by only modifying accounts after validations
        balance -= amount;
        try {
            other.deposit(amount);
        } catch (Exception e) {
            // Rollback if deposit fails
            balance += amount;
            throw e;
        }
    }

    public void addInterest() {
        if (interestRate < 0) {
            throw new IllegalStateException("Cannot apply negative interest rate");
        }
        balance = balance * (1 + interestRate);
    }

    public double getBalance() {
        return balance;
    }
}
