package BuildWeek2Team1.AziendaDiEnergia.exceptions;

import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
public class BadRequestException extends RuntimeException {
<<<<<<< HEAD
    private List<ObjectError> errors;
=======
    private List<ObjectError> errorsList;
>>>>>>> develop

    public BadRequestException(String message) {
        super(message);
    }

<<<<<<< HEAD
    public BadRequestException(List<ObjectError> errors) {
        this.errors = errors;
    }
=======
    public BadRequestException(List<ObjectError> errorsList) {
        super("Errori nel body");
        this.errorsList = errorsList;
    }

>>>>>>> develop
}