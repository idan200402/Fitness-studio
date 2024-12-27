package gym.Exception;

public class DuplicateClientException extends Exception {
    public DuplicateClientException() {
        super("~Error: The client is already registered for this lesson");
    }
    @Override
    public String getMessage(){
        return "~Error: The client is already registered";
    }
}
