package gym.customers;

public class Employee extends Person {
    private final String role;
    private final int salary;

    private Employee(Person p , String role, int salary) {
        super(p);
        this.role = role;
        this.salary = salary;
    }

    @Override
    public String toString() {
        String res = super.toString() + " | Role: " + role + " | ";
        if (role.equals("Secretary")) {
            res += "Salary per Month: " + salary;
        } else {
            res += "Salary per Hour: " + salary;
        }
        return res;
    }
}
