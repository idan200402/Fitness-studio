package gym.management.Sessions;

import gym.customers.Instructor;

public class PilatesSession extends Session{

    public PilatesSession(String date, ForumType forumType, Instructor instructor) {
        super(date, forumType, instructor);
    }

    @Override
    public SessionType getSessionType() {
        return SessionType.Pilates;
    }
}
