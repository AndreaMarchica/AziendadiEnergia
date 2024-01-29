package BuildWeek2Team1.AziendaDiEnergia.exceptions;

<<<<<<< HEAD

public class NotFoundException extends RuntimeException {
    public NotFoundException(long idNumero) {
        super("Elemento con id " + idNumero + " non trovato");
    }


=======
import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(UUID id) {
        super(id + " non trovato!");
    }
>>>>>>> develop
}