package gym.management.Sessions;

import gym.customers.Instructor;

public class ThaiBoxingSession extends Session{
    public ThaiBoxingSession(String date, ForumType forumType, Instructor instructor) {
        super(date, forumType, instructor);
        setPrice(100);
        setMAXCLIENTS(20);
    }

    @Override
    public String getSessionType() {
        return "ThaiBoxing";
    }

    @Override
    public String toString() {
        return "ThaiBoxing";
    }
}
