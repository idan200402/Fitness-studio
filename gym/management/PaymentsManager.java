package gym.management;

import gym.customers.Client;
import gym.customers.Instructor;
import gym.management.Sessions.Session;

public class PaymentsManager {
    private final Bank bank;
    public PaymentsManager(){
        this.bank = new Bank(0);
    }
    public void payInstructor(Instructor instructor){
        bank.withdraw(instructor.getSalary());
        instructor.setBalance(instructor.getBalance() + instructor.getSalary());
    }
    public void collectMoney(Client client , Session session){
        client.setBalance(client.getBalance() - session.getPrice());
        bank.deposit(session.getPrice());
    }
    public void paySecretary(Secretary secretary){
        bank.withdraw(secretary.getSalary());
        secretary.setBalance(secretary.getBalance() + secretary.getSalary());
    }
    public Bank getBank(){
        return bank;
    }

}
