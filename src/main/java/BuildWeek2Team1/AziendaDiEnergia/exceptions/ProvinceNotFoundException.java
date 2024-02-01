package BuildWeek2Team1.AziendaDiEnergia.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class ProvinceNotFoundException extends RuntimeException {
    public ProvinceNotFoundException(String message) {
        super(message);
    }
}