package gym.customers;

public class Client  {
    Person p;
    public Client(Person p) {
        this.p = p;
    }

    public String getName() {
        return p.getName();
    }
    public int getBalance(){
        return p.getBalance();
    }
    public void updateBalance(int charge){
        p.setBalance(p.getBalance()-charge);
    }
    public Gender getGender(){
        return p.getGender();
    }
    public String getBirthDay(){
        return p.getBirthday();
    }


}
//    public Client(String name, int balance, Gender gender, String birthDay) {
//        this.name = name;
//        this.balance = balance;
//        this.gender  = gender;
//        this.birthday = birthDay;
