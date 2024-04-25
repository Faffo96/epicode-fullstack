package bank;

public class BankAccount {
    String accountHolder;
    int transactions;
    final int maxTransactions = 50;
    double balance;

    BankAccount(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
        transactions = 0;
    }

    void withdraw(double amount) throws BankException {
        if (transactions < maxTransactions) {
            balance = balance - amount;
        } else {
            balance = balance - amount - 0.50;
        }
        transactions++;
    }

    double getBalance() {
        return balance;
    }
}
