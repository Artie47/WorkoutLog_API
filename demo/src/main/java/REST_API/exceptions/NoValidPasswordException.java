package REST_API.exceptions;

public class NoValidPasswordException extends Exception {
    public NoValidPasswordException(String message){
        super(message);
    }
}
