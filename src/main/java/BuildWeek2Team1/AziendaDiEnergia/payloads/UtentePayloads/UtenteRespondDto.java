package BuildWeek2Team1.AziendaDiEnergia.payloads.UtentePayloads;


import java.util.UUID;

public record UtenteRespondDto(
        UUID uuid,
        String username,
        String email
) {
}
