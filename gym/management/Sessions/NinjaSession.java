package gym.management.Sessions;

import gym.customers.Instructor;

public class NinjaSession extends Session{
    public NinjaSession(String date, ForumType forumType, Instructor instructor) {
        super(date, forumType, instructor);
    }

    @Override
    public SessionType getSessionType() {
        return SessionType.Ninja;
    }
}
