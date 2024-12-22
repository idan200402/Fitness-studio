package gym.management;

import gym.Exception.ClientNotRegisteredException;
import gym.Exception.DuplicateClientException;
import gym.Exception.InstructorNotQualifiedException;
import gym.Exception.InvalidAgeException;
import gym.customers.Client;
import gym.customers.Instructor;
import gym.customers.Person;
import gym.management.Sessions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Secretary {
    private static final ArrayList<Client> clients = new ArrayList<>();
    private static final ArrayList<Instructor> instructors = new ArrayList<>();
    private static final ArrayList<Session> sessions = new ArrayList<>();
    private boolean isValid = true;

    public Client registerClient(Person p) throws InvalidAgeException, DuplicateClientException {
        if (!validAge(p)) {
            throw new InvalidAgeException("~Error: Client must be at least 18 years old to register");
        }
        Client c = new Client(p);
        if (clientRegistered(c)) {
            throw new DuplicateClientException();
        }
        clients.add(c);
        return c;
    }

    public void unregisterClient(Client c2) throws ClientNotRegisteredException {
        if (!clients.remove(c2)) {
            throw new ClientNotRegisteredException();
        }

    }

    private boolean validAge(Person p) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate birtDay = LocalDate.parse(p.getBirthday(), format);
        LocalDate currDate = LocalDate.now();
        long years = ChronoUnit.YEARS.between(birtDay, currDate);
        return years >= 18;
    }

    private boolean clientRegistered(Client c) {
        return clients.contains(c);
    }

    public Instructor hireInstructor(Person p, int i, ArrayList<SessionType> sessionTypes) {
        Instructor instructor = new Instructor(p, i, sessionTypes);
        instructors.add(instructor);
        return instructor;
    }

    public Session addSession(SessionType sessionType, String date, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException {
        if (!instructor.getSessions().contains(sessionType)) {
            throw new InstructorNotQualifiedException();
        }
        Session session = switch (sessionType) {

            case Ninja -> new NinjaSession(date, forumType, instructor);
            case Pilates -> new PilatesSession(date, forumType, instructor);
            case MachinePilates -> new MachinePilatesSession(date, forumType, instructor);
            case ThaiBoxing -> new ThaiBoxingSession(date, forumType, instructor);
            default -> {
                throw new RuntimeException("shayome");
            }
        };

        sessions.add(session);
        return session;

    }

    private void isActive() {
        if (!isValid) {
            throw new RuntimeException("Secretary is not active");
        }
    }

    public void makeInactive() {
        isValid = false;
    }


    public void registerClientToLesson(Client c, Session s) {
        if(isSenior(c) && s.getForumType().)
    }

    public void paySalaries() {
    }

    private int getSessionCost(Session session) {
        switch (session.getSessionType()) {
            case Pilates -> {
                return 60;
            }
            case MachinePilates -> {
                return 80;
            }
            case Ninja -> {
                return 150;
            }
            case ThaiBoxing -> {
                return 100;
            }
            default -> throw new RuntimeException("Unknown Session");
        }
    }

    private boolean isSessionOccurred(Session session) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDate date = LocalDate.parse(session.getDate(), format);
        return date.isBefore(LocalDate.now());
    }

    private boolean isSenior(Client client) throws InvalidAgeException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate clientsBD = LocalDate.parse(client.getBirthDay(), format);
        LocalDate currentDate = LocalDate.now();
        int age = currentDate.getYear() - clientsBD.getYear();
        if (clientsBD.getMonthValue() > currentDate.getMonthValue() || (clientsBD.getMonthValue() == currentDate.getMonthValue()
                && clientsBD.getDayOfMonth() > currentDate.getDayOfMonth()){
            age--;
        }
        if(age<65){
            throw new InvalidAgeException("~Failed registration: Client doesn't meet the age requirements for this session (Seniors)");
        }
        return true;
    }
    private int getMaxCapacity(Session session) {
        switch (session.getSessionType()) {
            case Pilates -> {
                return 30;
            }
            case MachinePilates -> {
                return 10;
            }
            case Ninja -> {
                return 5;
            }
            case ThaiBoxing -> {
                return 20;
            }
            default -> throw new RuntimeException("Unknown Session");
        }
    }

}
