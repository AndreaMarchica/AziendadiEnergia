package BuildWeek2Team1.AziendaDiEnergia.payloads.clienti;

import BuildWeek2Team1.AziendaDiEnergia.entities.RagioneSociale;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record NewClienteDTO(
        @NotNull(message ="Campo obbligatorio")
        RagioneSociale ragioneSociale,
        @NotEmpty(message ="Campo obbligatorio")
        @Size(min = 11, max = 11, message ="La P.Iva non è corretta")
        String partitaIva,
        @NotEmpty(message = "L'email è obbligatoria")
        @Email(message = "L'email inserita non è valida")
        String email,
        @NotNull(message ="Campo obbligatorio")
        LocalDate dataUltimoContatto,
        @NotNull(message ="Campo obbligatorio")
        double fatturatoAnnuale,
        @NotEmpty(message = "L'email è obbligatoria")
        @Email(message = "L'email inserita non è valida")
        String pec,
        @NotNull(message ="Campo obbligatorio")
        String telefono,
        @NotEmpty(message = "L'email è obbligatoria")
        @Email(message = "L'email inserita non è valida")
        String emailContatto,
        @NotNull(message ="Campo obbligatorio")
        String nomeContatto,
        @NotNull(message ="Campo obbligatorio")
        String cognomeContatto,
        @NotNull(message ="Campo obbligatorio")
        String telefonoContatto,
        @NotNull(message ="Campo obbligatorio")
        String logoAziendale,
        @NotEmpty(message ="Campo obbligatorio")
        String indirizzo1Adress,
        @NotNull(message ="Campo obbligatorio")
        int indirizzo1Civico,
        @NotNull(message ="Campo obbligatorio")
        long indirizzo1Cap,
        @NotEmpty(message ="Campo obbligatorio")
        String indirizzo1Localita,

        @Nullable
        String indirizzo2Adress,
        @Nullable
        int indirizzo2Civico,
        @Nullable
        long indirizzo2Cap,
        @Nullable
        String indirizzo2Localita
) {
}
