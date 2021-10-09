package ka.piotr.organicbean.security.exceptions;

public class PasswordNotMatchException extends Exception{

    public PasswordNotMatchException(String message) {
        super(message);
    }
}
