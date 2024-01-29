package BuildWeek2Team1.AziendaDiEnergia.payloads.UtentePayloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UtenteRequestDto(

        @Size(min=3,message="username  troppo corto")
        @NotNull(message="il campo non deve essere null")
       String username,
        @Size(min=3,message="username  troppo corto")
        @NotNull(message="il campo non deve essere null")
       String email,
       @Email
       @NotNull
       String password,
        @Size(min=3,message="username  troppo corto")
        @NotNull(message="il campo non deve essere null")
       String nome,
        @Size(min=3,message="username  troppo corto")
        @NotNull(message="il campo non deve essere null")
       String cognome
) {
}
