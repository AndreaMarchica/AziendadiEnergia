package BuildWeek2Team1.AziendaDiEnergia.payloads;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record FatturaPostDTO(@NotNull(message = "Campo dell'importo Obbligatorio!") double importo,
                             @NotNull(message = "campo del ClienteID obbligatorio!") UUID clienteId,
                             @NotNull(message = "Campo del numero fattura obbligatorio!") String numeroFattura
) {
}