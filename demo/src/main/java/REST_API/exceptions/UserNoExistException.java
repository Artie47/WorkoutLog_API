package REST_API.exceptions;

public class UserNoExistException extends Exception{
    public UserNoExistException(String message){
        super(message);
    }
}
