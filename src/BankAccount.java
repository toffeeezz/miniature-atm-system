public record BankAccount(String accName, String accID, String accPIN, double accBalance) {


    public double accBalance() {
        return Math.round(accBalance * 100.0) / 100.0;
    }

    public BankAccount deposit(double amount){
        return new BankAccount( accName, accID, accPIN, accBalance + amount);
    }

    public BankAccount withdraw(double amount){
        return new BankAccount(accName, accID, accPIN, accBalance - amount);
    }
}
