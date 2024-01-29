package BuildWeek2Team1.AziendaDiEnergia.payloads;

import java.util.Date;
import java.util.List;

public record ErrorsResponseWithListDTO(String message, Date timeStamp, List<String> errorList) {
}