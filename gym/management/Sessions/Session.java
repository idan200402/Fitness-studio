package gym.management.Sessions;

import gym.customers.Client;
import gym.customers.Instructor;

import java.util.ArrayList;

public abstract class Session {
    private final String date;
    private final ForumType forumType;
    private final Instructor instructor;
    private final ArrayList<Client> clientsInSession = new ArrayList<>();

    public Session(String date, ForumType forumType, Instructor instructor) {
        this.date = date;
        this.forumType = forumType;
        this.instructor = instructor;
    }

    public String getDate() {
        return date;
    }

    public ForumType getForumType() {
        return forumType;
    }

    public Instructor getInstructor() {
        return instructor;
    }
    public void addToSession(Client client){
        clientsInSession.add(client);
    }
    public abstract SessionType getSessionType();
}
