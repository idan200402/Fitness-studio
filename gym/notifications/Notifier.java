package gym.notifications;

import gym.management.Sessions.Formatting.DateFormatForNotifyDate;
import gym.management.Sessions.Formatting.DateFormatStrategy;
import gym.management.Sessions.Formatting.LogDateFormat;
import gym.management.Sessions.Session;

import java.time.format.DateTimeFormatter;


public class Notifier {
    private DateFormatForNotifyDate format = new DateFormatForNotifyDate();
    private LogDateFormat logFormat = new LogDateFormat();
    public String notifySession( Session session, String message) {
        String sessionType = session.getSessionType();
        String date = session.getDate();
        return ("A message was sent to everyone registered for session " + sessionType + " on " + logFormat.format(date) + " : " + message);
    }
    public String notifyByDate(String date , String message){
        return ("A message was sent to everyone registered for a session on " + format.format(date) + " : " + message);
    }
    public String notifyAll(String message){
        return ("A message was sent to all gym clients: " + message);
    }

}
