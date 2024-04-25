package bank;

public class OnlineBankAccount extends BankAccount {

    double maxWithdrawal;

    public OnlineBankAccount(String accountHolder, double balance, double maxW) {
        super(accountHolder, balance);
        this.maxWithdrawal = maxW;
    }

    public void printBalance() {
        System.out.println("Owner: " + accountHolder + "- Balance: " + balance + " Number of transactions: " + transactions
                + "- Maximum transactions: " + maxTransactions + "- Maximum possible withdrawal: " + maxWithdrawal);
    }

    @Override
    public void withdraw(double amount) throws BankException {
        if (amount <= maxWithdrawal) {
            super.withdraw(amount);
        } else {
            throw new BankException("Withdraw limit:" + maxWithdrawal);
        }
    }
}

