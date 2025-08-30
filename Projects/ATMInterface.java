import java.util.*;

// Class to represent the user's Bank Account
class BankAccount {
    private double balance;

    public BankAccount() {
        this.balance = 0.0;  // default balance
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

// Interface for ATM operations
interface AtmOperationInterf {
    void viewBalance();
    void withdrawAmount(double withdrawAmount);
    void depositAmount(double depositAmount);
    void viewMiniStatement();
}

// Implementation of ATM operations (ATM Machine)
class AtmOperationImpl implements AtmOperationInterf {
    private BankAccount account;
    private Map<Double, String> ministmt = new LinkedHashMap<>();

    // constructor takes BankAccount object
    public AtmOperationImpl(BankAccount account) {
        this.account = account;
    }

    @Override
    public void viewBalance() {
        System.out.println("Available Balance is : " + account.getBalance());
    }

    @Override
    public void withdrawAmount(double withdrawAmount) {
        if (withdrawAmount <= 0) {
            System.out.println("Invalid amount! Please enter a positive number.");
            return;
        }
        if (withdrawAmount % 500 == 0) {
            if (withdrawAmount <= account.getBalance()) {
                ministmt.put(withdrawAmount, " Amount Withdrawn");
                System.out.println("Collect the Cash: " + withdrawAmount);
                account.setBalance(account.getBalance() - withdrawAmount);
                viewBalance();
            } else {
                System.out.println("Insufficient Balance !!");
            }
        } else {
            System.out.println("Please enter the amount in multiples of 500");
        }
    }

    @Override
    public void depositAmount(double depositAmount) {
        if (depositAmount <= 0) {
            System.out.println("Invalid deposit amount! Please enter a positive number.");
            return;
        }
        ministmt.put(depositAmount, " Amount Deposited");
        System.out.println(depositAmount + " Deposited Successfully !!");
        account.setBalance(account.getBalance() + depositAmount);
        viewBalance();
    }

    @Override
    public void viewMiniStatement() {
        if (ministmt.isEmpty()) {
            System.out.println("No transactions yet!");
        } else {
            System.out.println("----- Mini Statement -----");
            for (Map.Entry<Double, String> m : ministmt.entrySet()) {
                System.out.println(m.getKey() + " " + m.getValue());
            }
        }
    }
}

// Main class for User Interface
public class ATMInterface {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // hardcoded ATM number & PIN
        int atmNumber = 12345;
        int atmPin = 123;

        // Create Bank Account object
        BankAccount account = new BankAccount();
        AtmOperationInterf op = new AtmOperationImpl(account);

        System.out.println("Welcome to ATM Machine !!!");
        System.out.print("Enter ATM Number : ");
        int enteredNumber = in.nextInt();
        System.out.print("Enter Pin: ");
        int enteredPin = in.nextInt();

        if ((atmNumber == enteredNumber) && (atmPin == enteredPin)) {
            while (true) {
                System.out.println("\n===== ATM Menu =====");
                System.out.println("1. View Available Balance");
                System.out.println("2. Withdraw Amount");
                System.out.println("3. Deposit Amount");
                System.out.println("4. View Mini-statement");
                System.out.println("5. Exit");
                System.out.print("Enter Choice: ");
                int choice = in.nextInt();

                switch (choice) {
                    case 1:
                        op.viewBalance();
                        break;
                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = in.nextDouble();
                        op.withdrawAmount(withdrawAmount);
                        break;
                    case 3:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = in.nextDouble();
                        op.depositAmount(depositAmount);
                        break;
                    case 4:
                        op.viewMiniStatement();
                        break;
                    case 5:
                        System.out.println("Collect your ATM Card\nThank you for using ATM Machine!!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Please enter a correct choice.");
                }
            }
        } else {
            System.out.println("Incorrect ATM Number or PIN");
            System.exit(0);
        }
    }
}
