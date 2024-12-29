package gym.management;

import gym.Exception.ClientNotRegisteredException;
import gym.Exception.DuplicateClientException;
import gym.Exception.InstructorNotQualifiedException;
import gym.Exception.InvalidAgeException;
import gym.customers.*;
import gym.management.Sessions.*;
import gym.management.Sessions.Formatting.DateFormatStrategy;
import gym.management.Sessions.Formatting.LogDateFormat;
import gym.notifications.Notifier;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import gym.notifications.Notifier.*;
import gym.notifications.Observer;

public class Secretary extends Employee {
    private final ArrayList<Client> clients = new ArrayList<>();
    private final ArrayList<Instructor> instructors = new ArrayList<>();
    private final ArrayList<Session> sessions = new ArrayList<>();
    private boolean isValid = true;
    private final Log log = new Log();
    private final Notifier notifier = new Notifier();
    private static final PaymentsManager paymentsManager = new PaymentsManager();
    private final LogDateFormat format = new LogDateFormat();

    public Secretary(Person p, int salary) {
        super(p , salary);

    }
    public Client registerClient(Person p) throws InvalidAgeException, DuplicateClientException {
        isActive();
        if (!p.validAge()) {
            throw new InvalidAgeException("Error: Client must be at least 18 years old to register");
        }
        p = new Client(p);
        Client c=(Client)p;
        if (clientRegistered(c)) {
            throw new DuplicateClientException("Error: The client is already registered");
        }
        clients.add(c);
        log.addAction("Registered new client: " + c.getName());
        return c;
    }

    public void unregisterClient(Client c) throws ClientNotRegisteredException {
        isActive();
        if (clients.remove(c)) {
            log.addAction("Unregistered client: " + c.getName());
        } else {
            throw new ClientNotRegisteredException("Error: Registration is required before attempting to unregister");
        }

    }


    private boolean clientRegistered(Client c) {
        return clients.contains(c);
    }

    public Instructor hireInstructor(Person p, int i, ArrayList<SessionType> sessionTypes) {
        isActive();
        Instructor instructor = new Instructor(p, i, sessionTypes);
        instructors.add(instructor);
        log.addAction("Hired new instructor: " + instructor.getName() + " with salary per hour: " + i);
        return instructor;
    }

    public Session addSession(SessionType sessionType, String date, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException {
        isActive();
        if (!instructor.getSessions().contains(sessionType)) {
            throw new InstructorNotQualifiedException();
        }
        Session session = SessionFactory.createSession(sessionType, date, forumType, instructor);
        sessions.add(session);
        log.addAction("Created new session: " + session.getSessionType() + " on " + format.format(date) + " with instructor: " + instructor.getName());
        return session;

    }

    private void isActive() {
        if (!isValid) {
            throw new NullPointerException("Secretary is not active");
        }
    }

    public void makeInactive() {
        isValid = false;
    }


    public void registerClientToLesson(Client c, Session s) throws InvalidAgeException, DuplicateClientException, ClientNotRegisteredException {
        boolean flag = false;
        isActive();
        if(!clients.contains(c))
            throw new ClientNotRegisteredException("Error: The client is not registered with the gym and cannot enroll in lessons");
        if(s.getClientsInSession().contains(c))
            throw new DuplicateClientException("Error: The client is already registered for this lesson");
        if (s.isSessionOccurred()) {
            flag = true;
            log.addAction("Failed registration: Session is not in the future");
        }
        if ((s.getForumType() == ForumType.Male && c.getGender() != Gender.Male) || (s.getForumType() == ForumType.Female && c.getGender() != Gender.Female)) {
            flag = true;
            log.addAction("Failed registration: Client's gender doesn't match the session's gender requirements");
        }
        if (s.getPrice() > c.getBalance()) {
            flag = true;
            log.addAction("Failed registration: Client doesn't have enough balance");
        }
        if (!c.isSenior() && s.getForumType() == ForumType.Seniors) {
            flag = true;
            log.addAction("Failed registration: Client doesn't meet the age requirements for this session (Seniors)");
        }
        if (!clients.contains(c)) {
            flag = true;
            log.addAction("Failed registration: Client is already registered");
        }
        if(s.getClientsInSession().size() >= s.getMAXCLIENTS()){
            flag = true;
            log.addAction("Failed registration: No available spots for session");
        }
        if(s.getClientsInSession().contains(c)){
            flag = true;
            log.addAction("Failed registration: Client is already registered to this session");
        }
        if(!flag) {
            paymentsManager.collectMoney(c, s);
            s.addToSession(c);
            log.addAction("Registered client: " + c.getName() + " to session: " + s.getSessionType() + " on " + format.format(s.getDate()) + " for price: " + s.getPrice());
        }

    }

    public void paySalaries() {
        isActive();
        for (Session session : sessions) {
            paymentsManager.payInstructor(session.getInstructor());
        }
        paymentsManager.paySecretary(this);

        log.addAction("Salaries have been paid to all employees");

    }

    public void notify(Session session, String message) {
        isActive();
        log.addAction(notifier.notifySession(session, message));
        for (Client client : session.getClientsInSession()) {
            if(clients.contains(client)) {
                client.update(message);
            }
            else System.out.println("Error: The client is not registered with the gym and cannot enroll in lessons");
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
        for (Client client : clients){
                if(clients.contains(client))client.update(message);
                else System.out.println("Error: The client is not registered with the gym and cannot enroll in lessons");
        }

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
    public String getRole() {
        return "Secretary";
    }
    @Override
    public String getDescription() {
        return "Salary per Month: " + getSalary();
    }
    public PaymentsManager getPaymentsManager() {
        return paymentsManager;
    }
    public ArrayList<Client> getClients() {
        return clients;
    }
    public ArrayList<Instructor> getInstructors() {
        return instructors;
    }
    public ArrayList<Session> getSessions() {
        return sessions;
    }
    public Log getLog() {
        return log;
    }


}
