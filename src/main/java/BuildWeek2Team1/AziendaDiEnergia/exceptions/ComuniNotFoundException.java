package BuildWeek2Team1.AziendaDiEnergia.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ComuniNotFoundException extends RuntimeException {
    public ComuniNotFoundException(String message) {
        super(message);
    }
}