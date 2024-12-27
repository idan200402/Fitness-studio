package gym.management.Sessions;

import gym.Exception.DuplicateClientException;
import gym.customers.Client;
import gym.customers.Instructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public abstract class Session {
    private final String date;
    private final ForumType forumType;
    private final Instructor instructor;

    private  ArrayList<Client> clientsInSession;
    private int price ;
    private int MAXCLIENTS;
    public void setPrice(int price){
        this.price = price;
    }
    public int getPrice(){
        return price;
    }
    public int getMAXCLIENTS(){
        return MAXCLIENTS;
    }
    public void setMAXCLIENTS(int MAXCLIENTS){
        this.MAXCLIENTS = MAXCLIENTS;
    }

    public Session(String date, ForumType forumType, Instructor instructor) {
        this.date = date;
        this.forumType = forumType;
        this.instructor = instructor;
        clientsInSession = new ArrayList<>();
    }

    public String getDate() {
        return date;
    }

    public ForumType getForumType() {
        return forumType;
    }

    public Instructor getInstructor() {
        return instructor;
    }
    public ArrayList<Client> getClientsInSession() {
        return clientsInSession;
    }
    public void addToSession(Client client) throws DuplicateClientException {
        if(clientsInSession.contains(client)){
            throw new DuplicateClientException();
        }
        clientsInSession.add(client);
    }
    public abstract String getSessionType();
    public boolean isSessionOccurred() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDate date = LocalDate.parse(this.date, format);
        return date.isBefore(LocalDate.now());
    }
    public  String toString(){
        return "Session Type: " + getSessionType() + " | Date: " + date + " | Forum: " + forumType + " | Instructor: " + instructor.getName() + " | Participants: " + clientsInSession.size() + "/" + MAXCLIENTS;
    }
}
