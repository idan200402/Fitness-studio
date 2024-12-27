package gym.management.Sessions;

import gym.customers.Instructor;

public class NinjaSession extends Session{
    public NinjaSession(String date, ForumType forumType, Instructor instructor) {

        super(date, forumType, instructor);
        setPrice(150);
        setMAXCLIENTS(5);
    }

    @Override
    public String getSessionType() {
        return "Ninja";
    }

    @Override
    public String toString() {
        return "Ninja";
    }
}
