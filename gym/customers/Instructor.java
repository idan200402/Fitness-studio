package gym.customers;

import gym.management.Sessions.SessionType;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Instructor  extends Employee{
    private final ArrayList<SessionType> sessions;

    public ArrayList<SessionType> getSessions() {
        return sessions;
    }

    public Instructor(Person p, int hourlyWage, ArrayList<SessionType> sessions) {
        super(p , hourlyWage);
        this.sessions = new ArrayList<>(sessions);
    }


    @Override
    public String getRole() {
        return "Instructor";
    }

    @Override
     protected String getDescription() {
        return "Salary per Hour: " + super.getSalary() + " | Certified Classes: " + SessionType.arrayListToString(sessions);
    }
}
