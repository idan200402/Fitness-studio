package gym.Exception;

public class ClientNotRegisteredException extends Exception {
    public ClientNotRegisteredException() {
        super("~Error: Registration is required before attempting to unregister");
    }

    @Override
    public String getMessage() {
        return "~Error: Registration is required before attempting to unregister";
    }
}
