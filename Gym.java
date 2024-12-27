import gym.customers.Person;
import gym.management.Bank;
import gym.management.Secretary;

public class Gym {
    private static Gym gym;
    private String gymName;
    private Secretary secretary;

    private Gym(){
    }
    public static Gym getInstance(){
        if(gym == null){
            gym = new Gym();
        }
        return gym;
    }

    public void setName(String gymName) {
        this.gymName = gymName;
    }

    public void setSecretary(Person p, int salary) {
        if(secretary != null){
            Secretary newSecretary = new Secretary();
            secretary.transferData(newSecretary);
            secretary.makeInactive();
            secretary = newSecretary;
        }
        else {secretary = new Secretary();}

    }
    public Secretary getSecretary(){
        return secretary;
    }
    public String getGymName() {
        return gymName;
    }
    public Bank getBank(){
        return bank;
    }


}
