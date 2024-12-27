package gym.management;

public class Bank {
    private int balance;
    public Bank(int balance){
        this.balance = balance;
    }
    public void deposit(int amount){
        if(amount < 0){
            throw new IllegalArgumentException("wrong input");
        }
        balance += amount;
    }
    public void withdraw(int amount){
        if(amount < 0 ){
            throw new IllegalArgumentException("wrong input");
        }
        balance -= amount;
    }
    public int getBalance(){
        return balance;
    }
}
