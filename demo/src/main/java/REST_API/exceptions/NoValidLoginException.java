package REST_API.exceptions;

public class NoValidLoginException extends Exception {
    public NoValidLoginException(String message){
        super(message);
    }
}
