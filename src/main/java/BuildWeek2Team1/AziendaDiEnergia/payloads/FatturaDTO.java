package BuildWeek2Team1.AziendaDiEnergia.payloads;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public record FatturaDTO(@NotEmpty(message = "Campo dell'importo Obbligatorio!") double importo,
                         @NotEmpty(message = "Campo della data Obbligatorio!") LocalDate data
) {
}