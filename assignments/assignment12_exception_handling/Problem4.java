package assignments.assignment12_exception_handling;

/* Create multiple classes to imitate a bank structure with proper Exception Handling. For eg., if the user tries to withdraw more than the balance, throw "InsufficientBalanceException" (created customly) */

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException() {
        super("Insufficient balance in the account")
    }
    
    @Override
    public void printStackTrace() {
        System.out.println("Insufficient balance in the account");
    }
}

class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException();
        }
        balance -= amount;
        System.out.println("Amount withdrawn: " + amount);
        System.out.println("Remaining balance: " + balance);
    }

}

public class Problem4 {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);
        try {
            account.withdraw(500);
            account.withdraw(600);
        } catch (InsufficientBalanceException e) {
            e.printStackTrace();
        }
    }
}
