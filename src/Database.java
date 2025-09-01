import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class Database {

    private static Map<String, BankAccount> account;
    private static ArrayList<BankAccount> bankAccounts = new ArrayList<>();

    public static boolean checkValidAcc(String accNum) {
        for(BankAccount accounts: bankAccounts)
            if(accNum.equalsIgnoreCase(accounts.accID())){
                return true;
            }
        return false;
    }
    public static boolean checkPin(String accNumInput, String accPinInput){
        for(BankAccount accounts: bankAccounts)
            if (accNumInput.equalsIgnoreCase(accounts.accID()) && accPinInput.equalsIgnoreCase(accounts.accPIN())) {
                return true;
            }
        return false;
    }
    public static BankAccount getBankAccount(String identifier){
        for(BankAccount accounts: bankAccounts)
            if(identifier.equalsIgnoreCase(accounts.accID()) || identifier.equalsIgnoreCase(accounts.accName())){
                return accounts;
            }
        return null;
    }
    public static boolean checkExistingName(String nameInput){
        String[] inputSplits = nameInput.trim().split(", ");
        for(BankAccount accounts: bankAccounts) {
            String[] existingSplits = accounts.accName().trim().split(", ");
            if(inputSplits[0].equalsIgnoreCase(existingSplits[0]) && inputSplits[1].equalsIgnoreCase(existingSplits[1])){
                return true;
            }
        }
        return false;
    }
    public static void createAccount(String name, String pin){
        Random rand = new Random();
        boolean isDoneGenerating = false;
        while(!isDoneGenerating) {
            boolean valid = true;
            String genID = String.valueOf(rand.nextInt(100000, 1000000));
            for (BankAccount accounts : bankAccounts) {
                if (genID.equalsIgnoreCase(accounts.accID())) {
                    valid = false;
                }
            }
            if(valid){
                bankAccounts.add(new BankAccount(name, genID, pin, 0));
                isDoneGenerating = true;
            }
        }
    }
    public static void testAccounts(){
        bankAccounts.add(new BankAccount("Gosling, Ryan", "123456", "1234", 2300.3456));
    }
}
