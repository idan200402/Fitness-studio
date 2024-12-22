package gym.management.Sessions;

import gym.customers.Instructor;

public class ThaiBoxingSession extends Session{
    public ThaiBoxingSession(String date, ForumType forumType, Instructor instructor) {
        super(date, forumType, instructor);
    }

    @Override
    public SessionType getSessionType() {
        return SessionType.ThaiBoxing;
    }
}
