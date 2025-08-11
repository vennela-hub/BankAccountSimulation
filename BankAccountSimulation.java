import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Base class for Bank Account
class Account {
    protected double balance;
    protected List<String> transactionHistory;

    public Account() {
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: ₹" + amount);
            System.out.println("✅ Deposited: ₹" + amount);
        } else {
            System.out.println("❌ Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            transactionHistory.add("Withdrawn: ₹" + amount);
            System.out.println("✅ Withdrawn: ₹" + amount);
        } else {
            System.out.println("❌ Insufficient balance or invalid amount.");
        }
    }

    public double getBalance() {
        return balance;
    }

    public void getTransactionHistory() {
        System.out.println("\n📜 Transaction History:");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String t : transactionHistory) {
                System.out.println(t);
            }
        }
    }
}

// SavingsAccount with interest and withdrawal limit
class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(double interestRate) {
        super();
        this.interestRate = interestRate;
    }

    public void addInterest() {
        double interest = balance * interestRate / 100;
        balance += interest;
        transactionHistory.add("Interest added: ₹" + interest);
        System.out.println("💰 Interest added: ₹" + interest);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 10000) {
            System.out.println("⚠ Withdrawal limit exceeded (₹10,000).");
        } else {
            super.withdraw(amount);
        }
    }
}

public class BankAccountSimulation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SavingsAccount account = new SavingsAccount(5); // 5% interest rate

        while (true) {
            System.out.println("\n=== BANK MENU ===");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Add Interest");
            System.out.println("4. Check Balance");
            System.out.println("5. Transaction History");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ₹");
                    account.deposit(sc.nextDouble());
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ₹");
                    account.withdraw(sc.nextDouble());
                    break;
                case 3:
                    account.addInterest();
                    break;
                case 4:
                    System.out.println("💳 Current Balance: ₹" + account.getBalance());
                    break;
                case 5:
                    account.getTransactionHistory();
                    break;
                case 6:
                    System.out.println("🏦 Thank you for banking with us!");
                    sc.close();
                    return;
                default:
                    System.out.println("❌ Invalid option. Try again.");
            }
        }
    }
}