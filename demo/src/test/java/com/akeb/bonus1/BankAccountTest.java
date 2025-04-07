package com.akeb.bonus1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BankAccountTest {
    
    private BankAccount account;
    private static final double INITIAL_BALANCE = 1000.0;
    private static final double INTEREST_RATE = 0.05; // 5%
    private static final double DELTA = 0.001; // For floating-point comparisons
    
    @BeforeEach
    public void setUp() {
        account = new BankAccount(INITIAL_BALANCE, INTEREST_RATE);
    }
    
    @Test
    @DisplayName("Constructor should initialize balance and interest rate")
    public void testConstructor() {
        assertEquals(INITIAL_BALANCE, account.getBalance(), DELTA);
    }
    
    @Test
    @DisplayName("Deposit should increase balance by the given amount")
    public void testDeposit() {
        double depositAmount = 500.0;
        account.deposit(depositAmount);
        assertEquals(INITIAL_BALANCE + depositAmount, account.getBalance(), DELTA);
    }
    
    @Test
    @DisplayName("Deposit of zero should not change balance")
    public void testDepositZero() {
        account.deposit(0);
        assertEquals(INITIAL_BALANCE, account.getBalance(), DELTA);
    }
    
    @Test
    @DisplayName("Deposit of negative amount should throw IllegalArgumentException")
    public void testDepositNegative() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> account.deposit(-100)
        );
        assertEquals("Amount must be positive", exception.getMessage());
    }
    
    @Test
    @DisplayName("Withdraw should decrease balance by the given amount")
    public void testWithdraw() {
        double withdrawAmount = 300.0;
        account.withdraw(withdrawAmount);
        assertEquals(INITIAL_BALANCE - withdrawAmount, account.getBalance(), DELTA);
    }
    
    @Test
    @DisplayName("Withdraw of entire balance should result in zero balance")
    public void testWithdrawAll() {
        account.withdraw(INITIAL_BALANCE);
        assertEquals(0, account.getBalance(), DELTA);
    }
    
    @Test
    @DisplayName("Withdraw of zero should not change balance")
    public void testWithdrawZero() {
        account.withdraw(0);
        assertEquals(INITIAL_BALANCE, account.getBalance(), DELTA);
    }
    
    @Test
    @DisplayName("Withdraw of negative amount should throw IllegalArgumentException")
    public void testWithdrawNegative() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> account.withdraw(-100)
        );
        assertEquals("Amount must be positive", exception.getMessage());
    }
    
    @Test
    @DisplayName("Withdraw more than balance should throw IllegalStateException")
    public void testWithdrawExceedsBalance() {
        IllegalStateException exception = assertThrows(
            IllegalStateException.class,
            () -> account.withdraw(INITIAL_BALANCE + 100)
        );
        assertEquals("Insufficient balance", exception.getMessage());
    }
    
    @Test
    @DisplayName("Transfer should decrease source balance and increase destination balance")
    public void testTransfer() {
        BankAccount destAccount = new BankAccount(500, INTEREST_RATE);
        double transferAmount = 300.0;
        
        account.transfer(transferAmount, destAccount);
        
        assertEquals(INITIAL_BALANCE - transferAmount, account.getBalance(), DELTA);
        assertEquals(500 + transferAmount, destAccount.getBalance(), DELTA);
    }
    
    @Test
    @DisplayName("Transfer to null account should throw NullPointerException")
    public void testTransferToNullAccount() {
        NullPointerException exception = assertThrows(
            NullPointerException.class,
            () -> account.transfer(100, null)
        );
        assertEquals("Other account must not be null", exception.getMessage());
    }
    
    @Test
    @DisplayName("Transfer more than balance should throw IllegalStateException")
    public void testTransferExceedsBalance() {
        BankAccount destAccount = new BankAccount(500, INTEREST_RATE);
        
        IllegalStateException exception = assertThrows(
            IllegalStateException.class,
            () -> account.transfer(INITIAL_BALANCE + 100, destAccount)
        );
        assertEquals("Insufficient balance", exception.getMessage());
    }
    
    @Test
    @DisplayName("Transfer of negative amount should throw IllegalArgumentException")
    public void testTransferNegative() {
        BankAccount destAccount = new BankAccount(500, INTEREST_RATE);
        
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> account.transfer(-100, destAccount)
        );
        assertEquals("Amount must be positive", exception.getMessage());
    }
    
    @Test
    @DisplayName("Adding interest should increase balance by the interest rate")
    public void testAddInterest() {
        account.addInterest();
        double expectedBalance = INITIAL_BALANCE * (1 + INTEREST_RATE);
        assertEquals(expectedBalance, account.getBalance(), DELTA);
    }
    
    @Test
    @DisplayName("Adding interest to zero balance should result in zero")
    public void testAddInterestZeroBalance() {
        BankAccount zeroAccount = new BankAccount(0, INTEREST_RATE);
        zeroAccount.addInterest();
        assertEquals(0, zeroAccount.getBalance(), DELTA);
    }
    
    @Test
    @DisplayName("Adding zero interest should not change balance")
    public void testAddZeroInterest() {
        BankAccount zeroInterestAccount = new BankAccount(INITIAL_BALANCE, 0);
        zeroInterestAccount.addInterest();
        assertEquals(INITIAL_BALANCE, zeroInterestAccount.getBalance(), DELTA);
    }
    
    @Test
    @DisplayName("getBalance should return the current balance")
    public void testGetBalance() {
        assertEquals(INITIAL_BALANCE, account.getBalance(), DELTA);
        
        // Test after operations
        account.deposit(200);
        assertEquals(INITIAL_BALANCE + 200, account.getBalance(), DELTA);
        
        account.withdraw(300);
        assertEquals(INITIAL_BALANCE + 200 - 300, account.getBalance(), DELTA);
    }
}
