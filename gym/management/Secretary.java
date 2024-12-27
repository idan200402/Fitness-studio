package gym.management;

import gym.Exception.ClientNotRegisteredException;
import gym.Exception.DuplicateClientException;
import gym.Exception.InstructorNotQualifiedException;
import gym.Exception.InvalidAgeException;
import gym.customers.Client;
import gym.customers.Gender;
import gym.customers.Instructor;
import gym.customers.Person;
import gym.management.Sessions.*;
import gym.management.Sessions.Formatting.DateFormatStrategy;
import gym.notifications.Notifier;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import gym.notifications.Notifier.*;
import gym.notifications.Observer;

public class Secretary {
    private final ArrayList<Client> clients = new ArrayList<>();
    private final ArrayList<Instructor> instructors = new ArrayList<>();
    private final ArrayList<Session> sessions = new ArrayList<>();
    private boolean isValid = true;
    private final Log log = new Log();
    private final Notifier notifier = new Notifier();
    private final PaymentsManager paymentsManager = new PaymentsManager();

    public Client registerClient(Person p) throws InvalidAgeException, DuplicateClientException {
        isActive();
        if (!p.validAge()) {
            throw new InvalidAgeException("~Error: Client must be at least 18 years old to register");
        }
        Client c = new Client(p);
        if (clientRegistered(c)) {
            throw new DuplicateClientException();
        }
        clients.add(c);
        log.addAction("~Registered new client: " + c.getName());
        return c;
    }

    public void unregisterClient(Client c) throws ClientNotRegisteredException {
        isActive();
        if (clients.remove(c)) {
            log.addAction("~Unregistered client: " + c.getName());
        } else {
            throw new ClientNotRegisteredException();
        }

    }


    private boolean clientRegistered(Client c) {
        return clients.contains(c);
    }

    public Instructor hireInstructor(Person p, int i, ArrayList<SessionType> sessionTypes) {
        isActive();
        Instructor instructor = new Instructor(p, i, sessionTypes);
        instructors.add(instructor);
        log.addAction("~Hired new instructor: " + instructor.getName() + " with salary per hour: " + i);
        return instructor;
    }

    public Session addSession(SessionType sessionType, String date, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException {
        isActive();
        if (!instructor.getSessions().contains(sessionType)) {
            throw new InstructorNotQualifiedException();
        }
        Session session = SessionFactory.createSession(sessionType, date, forumType, instructor);
        sessions.add(session);
        log.addAction("Created new session: " + session.toString() + " on " + dateToLog(date) + " with instructor: " + instructor.getName());
        return session;

    }

    private void isActive() {
        if (!isValid) {
            throw new NullPointerException("~Secretary is not active");
        }
    }

    public void makeInactive() {
        isValid = false;
    }


    public void registerClientToLesson(Client c, Session s) throws InvalidAgeException, DuplicateClientException, ClientNotRegisteredException {
        isActive();
        if (!c.isSenior() && s.getForumType() == ForumType.Seniors) {
            log.addAction("~Failed registration: Client doesn't meet the age requirements for this session (Seniors)");
            return;
        }
        if ((s.getForumType() == ForumType.Male && c.getGender() != Gender.Male) || (s.getForumType() == ForumType.Female && c.getGender() != Gender.Female)) {
            log.addAction("~Failed registration: Client's gender doesn't match the session's gender requirements");
            return;
        }
        if (s.isSessionOccurred()) {
            log.addAction("~Failed registration: Session is not in the future");
            return;
        }
        if (s.getPrice() > c.getBalance()) {
            log.addAction("~Failed registration: Client doesn't have enough balance");
            return;
        }
        if (!clients.contains(c)) {
            log.addAction("~Failed registration: Client is already registered");
            return;
        }
        c.setBalance(c.getBalance() - s.getPrice());
        s.addToSession(c);
        log.addAction("~Registered client: " + c.getName() + " to session: " + s.getSessionType() + " on " + s.getDate() + " for price :" + s.getPrice());

    }

    public void paySalaries() {
        isActive();
        for (Session session : sessions) {
            paymentsManager.payInstructor(session.getInstructor());
            paymentsManager.collectMoney(session);
        }

    }

    public void notify(Session session, String message) {
        isActive();
        log.addAction(notifier.notifySession(session, message));
        for (Observer observer : session.getClientsInSession()) {
            observer.update(message);
        }
    }

    public void notify(String date, String message) {
        isActive();
        LocalDate currDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        boolean sessionExists = false;
        for (Session session : sessions) {
            LocalDate sessionDate = LocalDate.parse(session.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
            if (sessionDate.equals(currDate)) {
                sessionExists = true;
                for (Observer observer : session.getClientsInSession()) {
                    observer.update(message);
                }
            }
        }
        if (sessionExists) {
            log.addAction(notifier.notifyByDate(date, message));
        }
    }

    public void notify(String message) {
        isActive();
        log.addAction(notifier.notifyAll(message));
        for (Client client : clients) {
            client.update(message);
        }
    }

    private String dateToLog(String date) {
        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, format1);
        DateTimeFormatter format2 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return dateTime.format(format2);
    }

    public void printActions() {
        isActive();
        log.print();
    }

    public void transferData(Secretary newSecretary) {
        isActive();
        newSecretary.clients.addAll(clients);
        newSecretary.instructors.addAll(instructors);
        newSecretary.sessions.addAll(sessions);
        newSecretary.log.add(log);
    }
    @Override
    public String toString() {
        return "Secretary";
    }

}
