package rocha.andre.project.infra.exceptions;

public class DontHaveEnoughFunds extends RuntimeException {
    public DontHaveEnoughFunds(String message) {
        super(message);
    }
}
