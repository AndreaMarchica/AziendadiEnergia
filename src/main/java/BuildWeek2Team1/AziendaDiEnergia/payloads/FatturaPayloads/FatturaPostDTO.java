package BuildWeek2Team1.AziendaDiEnergia.payloads.FatturaPayloads;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Locale;
import java.util.UUID;

public record FatturaPostDTO(@Min(value=1,message = "Campo dell'importo Obbligatorio!") double importo,
                             @NotNull(message = "campo del ClienteID obbligatorio!") UUID clienteId,
                             @NotNull(message = "data obbligatoria")LocalDate data

                             ) {
}