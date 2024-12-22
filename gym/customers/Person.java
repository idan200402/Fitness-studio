package gym.customers;

public class Person {
    private final String name;
    private    int balance;
    private final Gender gender;
    private final String birthday;

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public Gender getGender() {
        return gender;
    }

    public String getBirthday() {
        return birthday;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Person(String name, int balance, Gender gender, String birthDay) {
        this.name = name;
        this.balance = balance;
        this.gender  = gender;
        this.birthday = birthDay;

    }
    public Person(Person p){
        this.name = p.getName();
        this.birthday = p.getBirthday();
        this.gender = p.getGender();
        this.balance = p.getBalance();
    }



}
