package BuildWeek2Team1.AziendaDiEnergia.payloads.errors;

import java.util.Date;
import java.util.List;

public record ErrorsDTOWithList(
        String message,
        Date timestamp,
        List<String> errorsList
) {
}