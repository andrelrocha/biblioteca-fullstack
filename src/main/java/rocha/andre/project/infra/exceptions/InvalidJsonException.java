package rocha.andre.project.infra.exceptions;

public class InvalidJsonException extends RuntimeException {

    public InvalidJsonException(String message) {
        super(message);
    }
}
