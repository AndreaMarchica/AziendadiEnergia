package BuildWeek2Team1.AziendaDiEnergia.payloads.ErrorPayloads;

import java.time.LocalDateTime;

public record ErrorDto(
        String message,
        LocalDateTime data
) {
}
