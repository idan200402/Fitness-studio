import gym.customers.Employee;
import gym.customers.Person;
import gym.management.Bank;
import gym.management.Secretary;
import gym.management.Sessions.Session;

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
            Secretary newSecretary = new Secretary(p , salary);
            secretary.transferData(newSecretary);
            secretary.makeInactive();
            secretary = newSecretary;
        }
        else {
            secretary = new Secretary(p , salary);
        }
        secretary.getLog().addAction("A new secretary has started working at the gym: " + secretary.getName());

    }
    public Secretary getSecretary(){
        return secretary;
    }
    public String getGymName() {
        return gymName;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Gym Name: ").append(gymName).append("\n");
        builder.append("Gym Secretary: ").append(secretary).append("\n");
        builder.append("Gym Balance: ").append(secretary.getPaymentsManager().getBank().getBalance()).append("\n\n");
        builder.append("Clients Data:\n");
        if (!secretary.getClients().isEmpty()) {
            for (Person client : secretary.getClients()) {
                builder.append(client.toString()).append("\n");
            }
        }
        builder.append("\n");
        builder.append("Employees Data:\n");
        if (!secretary.getInstructors().isEmpty()) {
            for (Employee employee : secretary.getInstructors()) {
                builder.append(employee.toString()).append("\n");
            }
        }
        builder.append(secretary).append("\n");
        builder.append("\n");
        builder.append("Sessions Data:\n");
        if (!secretary.getSessions().isEmpty()) {
            for (Session session : secretary.getSessions()) {
                builder.append(session).append("\n");
            }
        }
        return builder.toString();
    }

}
