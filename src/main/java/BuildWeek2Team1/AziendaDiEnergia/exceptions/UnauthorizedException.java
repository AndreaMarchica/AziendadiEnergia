package BuildWeek2Team1.AziendaDiEnergia.exceptions;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}