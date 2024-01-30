package BuildWeek2Team1.AziendaDiEnergia.exceptions;

public class EmailAlreadyInDbException extends  RuntimeException{
    public EmailAlreadyInDbException(String email) {
        super(email+" gia esistente, selezionane un'altra");
    }
}
