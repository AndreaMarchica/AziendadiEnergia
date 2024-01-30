package BuildWeek2Team1.AziendaDiEnergia.payloads.ErrorPayloads;

import java.time.LocalDateTime;

public record ErrorDTO(
        String message,
        LocalDateTime data
) {
}
