package gym.customers;

import gym.management.Sessions.SessionType;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Instructor  extends Person{
    private final int hourlyWage;
    private final ArrayList<SessionType> sessions;



    public int getHourlyWage() {
        return hourlyWage;
    }

    public ArrayList<SessionType> getSessions() {
        return sessions;
    }

    public Instructor(Person p, int hourlyWage, ArrayList<SessionType> sessions) {
        super(p);
        this.hourlyWage = hourlyWage;
        this.sessions = new ArrayList<>(sessions);
    }
    @Override
    public String toString() {
        return super.toString() + " | Role: Instructor | Salary per Hour: " + hourlyWage + " | Certified Classes: " + sessions.stream().map(SessionType::toString).collect(Collectors.joining(", "));
    }



}
