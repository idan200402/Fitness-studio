package gym.customers;

public abstract class Employee extends Person {
    private final int salary;

    protected Employee(Person p, int salary) {
        super(p);
        this.salary = salary;
    }

    @Override
    public String toString() {
        return super.toString() + " | Role: " + getRole() + " | " + getDescription();
    }
    public abstract String getRole();
    protected abstract String getDescription();
    public int getSalary() {
        return salary;
    }
}
