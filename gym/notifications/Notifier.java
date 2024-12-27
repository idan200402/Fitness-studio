package gym.notifications;

import gym.management.Sessions.Session;


public class Notifier {

    public String notifySession( Session session, String message) {
        String sessionType = session.getSessionType().toString();
        String date = session.getDate();
        return ("~A message was sent to everyone registered for session " + sessionType + " on " + date + " : " + message);
    }
    public String notifyByDate(String date , String message){
        return ("~A message was sent to everyone registered for a session on " + date + " : " + message);
    }
    public String notifyAll(String message){
        return ("~A message was sent to all gym clients: " + message);
    }

}
