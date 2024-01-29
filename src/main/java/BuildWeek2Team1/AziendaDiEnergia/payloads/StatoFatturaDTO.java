package BuildWeek2Team1.AziendaDiEnergia.payloads;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record StatoFatturaDTO(
        @NotNull(message = "Lo stato è un campo obbligatorio!")
        @Size(min = 3, max = 12, message = "Il nome deve essere compreso tra 3 e 12 caratteri")
        String stato
) {
}