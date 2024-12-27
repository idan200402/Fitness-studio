package gym.management;

import gym.Exception.InvalidAgeException;
import gym.customers.Client;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Log {
    private final ArrayList<String> actions = new ArrayList<>();
    public void addAction(String action){
        actions.add(action);
    }
    public void print(){
        for(String action : actions){
            System.out.println(action);
        }
    }
    public void add(Log Log){
        actions.addAll(Log.actions);
    }


}
