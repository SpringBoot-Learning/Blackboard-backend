package springboot.postgresql.Blackboard.exception;

public class AuthorisationException extends Exception {

    private static final long serialVersionUID = 1L;

    public AuthorisationException(String lmessage) {
        super(lmessage);
    }
}
