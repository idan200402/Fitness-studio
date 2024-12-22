package gym.Exception;

public class InvalidAgeException extends Exception {
    public InvalidAgeException(String string) {
    }
    @Override
    public String getMessage(){
        return "~Error: Client must be at least 18 years old to register";
    }

}
