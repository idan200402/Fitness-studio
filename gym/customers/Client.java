package gym.customers;

import gym.Exception.InvalidAgeException;
import gym.notifications.Observer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Client extends Person implements Observer {
    private final ArrayList<String> notifications = new ArrayList<>();

    public Client(String name, int balance, Gender gender, String birthDay) {
        super(name, balance, gender, birthDay);
    }

    public Client(Person p) {
        super(p);
    }

    public boolean isSenior() throws InvalidAgeException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate clientBD = LocalDate.parse(this.getBirthday(), format);
        LocalDate currentDate = LocalDate.now();
        int age = currentDate.getYear() - clientBD.getYear();
        if (clientBD.getMonthValue() > currentDate.getMonthValue() || (clientBD.getMonthValue() == currentDate.getMonthValue()
                && clientBD.getDayOfMonth() > currentDate.getDayOfMonth())) {
            age--;
        }
        return age >= 65;
    }


    @Override
    public void update(String message) {
        notifications.add(message);
    }

    public ArrayList<String> getNotifications() {
        return notifications;
    }

}

