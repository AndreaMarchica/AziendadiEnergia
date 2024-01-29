package BuildWeek2Team1.AziendaDiEnergia.payloads;

import jakarta.validation.constraints.NotNull;

public record FatturaPostDTO(@NotNull(message = "Campo dell'importo Obbligatorio!") double importo,
                             @NotNull(message = "campo del ClienteID obbligatorio!") long clienteId,
                             @NotNull(message = "Campo del numero fattura obbligatorio!") String numeroFattura
) {
}