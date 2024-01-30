package BuildWeek2Team1.AziendaDiEnergia.payloads.FatturaPayloads;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record FatturaPostDTO(@NotNull(message = "Campo dell'importo Obbligatorio!") double importo,
                             @NotNull(message = "campo del ClienteID obbligatorio!") UUID clienteId

) {
}