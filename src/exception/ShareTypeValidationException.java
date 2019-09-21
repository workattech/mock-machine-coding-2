package exception;

public class ShareTypeValidationException extends IllegalArgumentException{
    public ShareTypeValidationException(final String msg) {
        super(msg);
    }
}
