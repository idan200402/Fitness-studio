package gym.customers;

import gym.Exception.InvalidAgeException;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public  class Person {
    private final String name;
    private    int balance;
    private final Gender gender;
    private final String birthday;
    private String id;


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
        this.id = IDGenerate.generate();

    }
    public Person(Person p) {
        this.name = p.getName();
        this.birthday = p.getBirthday();
        this.gender = p.getGender();
        this.balance = p.getBalance();
    }
    public boolean validAge() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate birtDay = LocalDate.parse(birthday, format);
        LocalDate currDate = LocalDate.now();
        long years = ChronoUnit.YEARS.between(birtDay, currDate);
        return years >= 18;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return name.equals(person.name) && birthday.equals(person.birthday);
    }
    @Override
    public String toString() {
        return "    ~ID: " + id + " | Name: " + name + " | Gender: " + gender +
                " | Birthday: " + birthday + " | Age: " + ageCalculator() +
                " | Balance: " + balance;
    }

    private int ageCalculator() {
        LocalDate date  = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return Period.between(date, LocalDate.now()).getYears();

    }

}
