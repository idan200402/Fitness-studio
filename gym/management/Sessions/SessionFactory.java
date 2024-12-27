package gym.management.Sessions;

import gym.customers.Instructor;

public class SessionFactory {
    public static Session createSession(SessionType sessionType, String date, ForumType forumType, Instructor instructor) {
        return switch (sessionType) {
            case ThaiBoxing -> new ThaiBoxingSession(date, forumType, instructor);
            case Pilates -> new PilatesSession(date, forumType, instructor);
            case Ninja -> new NinjaSession(date, forumType, instructor);
            case MachinePilates -> new MachinePilatesSession(date, forumType, instructor);
            default -> null;
        };
    }
}
