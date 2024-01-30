package BuildWeek2Team1.AziendaDiEnergia.payloads.clienti;

import BuildWeek2Team1.AziendaDiEnergia.entities.RagioneSociale;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record NewClienteDTO(
        @NotNull(message ="Ragione sociale obbligatoria")
        RagioneSociale ragioneSociale,
        @NotEmpty(message ="Partita Iva obbligatoria")
        @Size(min = 11, max = 11, message ="La P.Iva deve contenere 11 cifre")
        String partitaIva,
        @NotEmpty(message = "L'email è obbligatoria")
        @Email(message = "L'email inserita non è valida")
        String email,
        @NotNull(message ="Data ultimo contatto obbligatoria")
        LocalDate dataUltimoContatto,
        @NotNull(message ="Fatturato annuale obbligatorio")
        double fatturatoAnnuale,
        @NotEmpty(message = "La pec è obbligatoria")
        @Email(message = "L'email inserita non è valida")
        String pec,
        @NotNull(message ="Numero di telefono obbligatorio")
        String telefono,
        @NotEmpty(message = "L'email contatto è obbligatoria")
        @Email(message = "L'email inserita non è valida")
        String emailContatto,
        @NotNull(message ="Il nome del contatto è obbligatorio")
        String nomeContatto,
        @NotNull(message ="Il cognome del contatto è obbligatorio")
        String cognomeContatto,
        @NotNull(message ="Il telefono del contatto è obbligatorio")
        String telefonoContatto,
        @NotNull(message ="Il logo aziendale obbligatorio")
        String logoAziendale
) {
}
