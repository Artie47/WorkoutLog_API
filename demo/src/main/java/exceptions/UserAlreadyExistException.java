package app.sport.workoutlog.exceptions;

public class UserAlreadyExistException extends Exception{
    public UserAlreadyExistException(String message){
        super (message);
    }
}
