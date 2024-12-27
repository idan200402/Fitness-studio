package gym.management.Sessions;

import gym.customers.Client;
import gym.customers.Instructor;

import java.util.ArrayList;

public class MachinePilatesSession extends Session {



    public MachinePilatesSession(String date, ForumType forumType, Instructor instructor ) {
        super(date, forumType, instructor );
        setPrice(80);
        setMAXCLIENTS(10);
    }

    @Override
    public String getSessionType() {
        return "MachinePilates";
    }

    @Override
    public String toString() {
        return "MachinePilates";
    }
}
