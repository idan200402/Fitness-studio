package gym.Exception;

public class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
    public InvalidAgeException() {
        super("~Error: Client must be at least 18 years old to register");
    }
    @Override
    public String getMessage(){
        return "~Error: Client must be at least 18 years old to register";
    }

}
