package BuildWeek2Team1.AziendaDiEnergia.payloads.clienti;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UtenteUpdateRequestDto(
        @Size(min = 3, message = "username troppo corto")
        String username,
        @Size(min = 3, message = "nome troppo corto")
        String nome,
        @Size(min = 3, message = "cognome troppo corto")
        String cognome
) {
}
