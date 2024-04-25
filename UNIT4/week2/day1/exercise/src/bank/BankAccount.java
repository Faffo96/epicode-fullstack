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
        if (amount < balance) {
            if (transactions < maxTransactions) {
                balance = balance - amount;
            } else {
                balance = balance - amount - 0.50;
            }
        } else {
            throw new BankException("Insufficient balance.");
        }
        transactions++;
    }

    double getBalance() {
        return balance;
    }
}
