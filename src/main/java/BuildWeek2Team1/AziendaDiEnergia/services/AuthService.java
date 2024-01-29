package BuildWeek2Team1.AziendaDiEnergia.services;

import BuildWeek2Team1.AziendaDiEnergia.entities.Utente;
import BuildWeek2Team1.AziendaDiEnergia.exceptions.UnauthorizedException;
import BuildWeek2Team1.AziendaDiEnergia.payloads.AuthPayloads.AuthRequestDTO;
import BuildWeek2Team1.AziendaDiEnergia.security.JWTTtools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private JWTTtools jwtTtools;

    public String authenticateUser(AuthRequestDTO body){
        Utente utente=  utenteService.findByEmail(body.email());
        if (bcrypt.matches(body.password(), utente.getPassword())) {
            return jwtTtools.createToken(utente);
        } else {
            throw new UnauthorizedException("Credenziali non valide!");
        }

    }
}
