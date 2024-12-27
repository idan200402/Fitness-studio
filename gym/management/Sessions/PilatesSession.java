package gym.management.Sessions;

import gym.customers.Instructor;

public class PilatesSession extends Session{


    public PilatesSession(String date, ForumType forumType, Instructor instructor) {
        super(date, forumType, instructor);
        setPrice(60);
        setMAXCLIENTS(30);
    }

    @Override
    public String getSessionType() {
        return "Pilates";
    }


}
