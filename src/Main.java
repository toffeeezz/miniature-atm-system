import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {


    static String userAccNum;
    static String userAccPIN;
    static BankAccount bankAccount;
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Database.testAccounts();
        HomePage();

    }

    static void HomePage() {
        boolean state = true;
        String input;
        while (state) {
            Utils.LineBreak();
            Utils.SimplePrint("Bank Central");
            Utils.SimplePrint("----------------------");
            Utils.SimplePrint("1. Login\n2. Sign up\n3. Exit");
            Utils.LinePrint("> ");
            input = scan.nextLine();
            switch (input) {
                case "1":
                    state = false;
                    LoginPage();
                    break;
                case "2":
                    state = false;
                    SignUpPage();
                case "3":
                    Utils.SimplePrint("Thanks for using our Bank!!");
                    System.exit(0);
                    break;
                default:
                    Utils.LineBreak();
                    Utils.SimplePrint(">>Invalid input. Please Try again<<");
            }
        }
    }

    static void LoginPage() {
        boolean inLogin = true;
        while (inLogin) {
            Utils.LineBreak();
            Utils.SimplePrint("Enter your (6) digit Account Number.\nType EXIT to return to homepage.");
            Utils.PrintDesign();
            userAccNum = scan.nextLine();
            if (Database.checkValidAcc(userAccNum)) {

                boolean enteredPassword = false;
                while (!enteredPassword) {

                    Utils.LineBreak();
                    Utils.SimplePrint("Account found!!\nEnter your (4) digit PIN.");
                    Utils.SimplePrint("Type EXIT to return to homepage.");
                    Utils.PrintDesign();
                    userAccPIN = scan.nextLine();

                    if (Database.checkPin(userAccNum, userAccPIN)) {
                        Utils.SimplePrint("Successfully logged in!!");
                        AccountPage();
                        inLogin = false;
                        enteredPassword = true;
                    } else {
                        Utils.LineBreak();
                        Utils.SimplePrint(">>You've entered the incorrect PIN. Try again<<");
                    }
                }
            } else if (userAccNum.equalsIgnoreCase("exit")) {
                HomePage();
                inLogin = false;
            } else {
                Utils.LineBreak();
                Utils.SimplePrint(">>Account Number does not exist<<");
            }
        }
    }

    static void AccountPage() {
        bankAccount = Database.getBankAccount(userAccNum);
        assert bankAccount != null : "Bank Account must exist";
        String[] nameSplits = bankAccount.accName().split(", ");
        double balance = bankAccount.accBalance();

        Utils.LineBreak();
        Utils.FormatPrint("Account Name: %s %s", nameSplits[1], nameSplits[0]);
        Utils.FormatPrint("\nBalance: $%.2f%n", balance);

    }

    static void SignUpPage() {
        boolean isSigningUp = true;
        String name = "";
        String idNumber;
        String pin = "";

        while (isSigningUp) {
            boolean isInputtingName = true;
            while (isInputtingName) {
                Utils.LineBreak();
                Utils.SimplePrint("Enter your full name (eg. Last Name, First Name).");
                Utils.PrintDesign();
                name = scan.nextLine();

                if (Utils.FilterCharacters(name)) {
                    isInputtingName = false;
                } else {
                    Utils.LineBreak();
                    Utils.SimplePrint(">>Invalid characters. Please try again<<");
                }
                if (!Database.checkExistingName(name)) {
                    boolean isInputtingPin = true;

                    while (isInputtingPin) {
                        Utils.LineBreak();
                        Utils.SimplePrint("Enter a (4) digit PIN");
                        Utils.PrintDesign();
                        int inputPin;

                        try {
                            inputPin = scan.nextInt();
                            scan.nextLine();
                            if (String.valueOf(inputPin).length() == 4) {
                                pin = String.valueOf(inputPin);
                                isInputtingPin = false;
                            } else {
                                Utils.LineBreak();
                                Utils.SimplePrint(">>Incorrect number of digits. Please try again<<");
                            }
                        } catch (InputMismatchException e) {
                            Utils.LineBreak();
                            Utils.SimplePrint(">>Non-numerical characters are not allowed. Please try again<<");
                            scan.nextLine();
                        }
                    }

                    boolean valid = false;

                    while (!valid) {
                        Utils.LineBreak();
                        Utils.FormatPrint("Name: %s\nPIN: %s", name, pin);
                        Utils.SimplePrint("\nPlease confirm the details you've entered above before proceeding.");
                        Utils.SimplePrint("----------------------");
                        Utils.SimplePrint("1. Confirm\n2. Reset");

                        String input = scan.nextLine();
                        switch (input) {
                            case "1":
                                valid = true;
                                isSigningUp = false;
                                break;
                            case "2":
                                valid = true;
                                break;
                            default:
                                Utils.LineBreak();
                                Utils.SimplePrint(">>Invalid input. Please Try again<<");
                        }
                    }

                    Database.createAccount(name, pin);

                    bankAccount = Database.getBankAccount(name);

                    assert bankAccount != null: "Account name is not null!";

                    String accName = bankAccount.accName();
                    String accID = bankAccount.accID();
                    String accPIN = bankAccount.accPIN();
                    String[] splits = accName.split(", ");

                    Utils.LineBreak();
                    Utils.SimplePrint("Created an account!!");
                    Utils.FormatPrint("Name: %s %s", splits[1], splits[1]);
                    Utils.SimplePrint("\nAccount Number: " + accID);
                    Utils.SimplePrint("PIN: " + accPIN);
                    Utils.SimplePrint("Press enter to proceed to the login page.");
                    Utils.PrintDesign();
                    String input = scan.nextLine();
                    LoginPage();

                } else {
                    Utils.LineBreak();
                    Utils.SimplePrint(">>An account already exists under that name. Please contact your local bank for support<<");
                }
            }
        }
    }
}