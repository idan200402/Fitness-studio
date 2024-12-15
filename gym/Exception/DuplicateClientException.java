package gym.Exception;

public class DuplicateClientException extends Exception {
    @Override
    public String getMessage(){
        return "~Error: The client is already registered";
    }
}
