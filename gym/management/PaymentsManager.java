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
        instructor.setBalance(instructor.getBalance() + instructor.getHourlyWage());
        bank.withdraw(instructor.getHourlyWage());
    }
    public void collectMoney(Session session){
        for(Client c : session.getClientsInSession()){
            c.setBalance(c.getBalance() - session.getPrice());
            bank.deposit(session.getPrice());
        }
    }

}
