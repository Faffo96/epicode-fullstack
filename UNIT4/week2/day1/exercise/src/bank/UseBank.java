package bank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UseBank {
    static Logger logger = LoggerFactory.getLogger("root");

    public static void main(String[] args) {

        BankAccount account1 = new BankAccount("Mario Grossi", 20000.0);

        System.out.println("Account balance: " + account1.getBalance());

        try {
            account1.withdraw(177750.5);
            System.out.println("Account balance: " + account1.getBalance());
        } catch (BankException e) {
            System.out.println("Error during withdrawal: " + e);
            e.printStackTrace();
            logger.error(account1.accountHolder + " - " + e.getMessage());
        }

        OnlineBankAccount account2 = new OnlineBankAccount("Luigi Rossi", 50350.0, 1500);
        account2.printBalance();

        try {
            account2.withdraw(2000);
            account2.printBalance();
        } catch (BankException e) {
            System.out.println("Error during withdrawal: " + e);
            e.printStackTrace();
            logger.error(account2.accountHolder + " - " + e.getMessage());
        }
    }
}
