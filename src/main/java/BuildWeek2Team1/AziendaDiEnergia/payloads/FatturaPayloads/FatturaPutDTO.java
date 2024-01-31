package BuildWeek2Team1.AziendaDiEnergia.payloads.FatturaPayloads;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record FatturaPutDTO(@NotNull(message = "Campo dell'importo Obbligatorio!") double importo,
                            @NotNull(message = "campo del ClienteID obbligatorio!") UUID clienteId,
                            @NotNull(message = "Campo dello stato obbligatorio!") String statoFattura,
                            @NotNull(message = "data obbligatoria") LocalDate data
) {
}