package gym.customers;

import gym.management.Sessions.SessionType;

import java.util.ArrayList;

public class Instructor {
    private Person p;
    private int hourlyWage;
    private ArrayList<SessionType> sessions;

    public Person getP() {
        return p;
    }

    public int getHourlyWage() {
        return hourlyWage;
    }

    public ArrayList<SessionType> getSessions() {
        return sessions;
    }

    public Instructor(Person p, int hourlyWage, ArrayList<SessionType> sessions) {
        this.p = p;
        this.hourlyWage = hourlyWage;
        this.sessions = new ArrayList<>(sessions);
    }



}
