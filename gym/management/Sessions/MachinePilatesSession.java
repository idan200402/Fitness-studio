package gym.management.Sessions;

import gym.customers.Instructor;

public class MachinePilatesSession extends Session {

    public MachinePilatesSession(String date, ForumType forumType, Instructor instructor) {
        super(date, forumType, instructor);
    }

    @Override
    public SessionType getSessionType() {
        return SessionType.MachinePilates;
    }
}
