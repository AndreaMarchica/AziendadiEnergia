package BuildWeek2Team1.AziendaDiEnergia.services;

import BuildWeek2Team1.AziendaDiEnergia.entities.Role;
import BuildWeek2Team1.AziendaDiEnergia.entities.Utente;
import BuildWeek2Team1.AziendaDiEnergia.exceptions.EmailAlreadyInDbException;
import BuildWeek2Team1.AziendaDiEnergia.exceptions.UnauthorizedException;
import BuildWeek2Team1.AziendaDiEnergia.payloads.AuthPayloads.AuthRequestDTO;
import BuildWeek2Team1.AziendaDiEnergia.payloads.AuthPayloads.TokenResponseDTO;
import BuildWeek2Team1.AziendaDiEnergia.payloads.UtentePayloads.UtenteRequestDto;
import BuildWeek2Team1.AziendaDiEnergia.payloads.UtentePayloads.UtenteRespondDto;
import BuildWeek2Team1.AziendaDiEnergia.repositories.UtenteRepository;
import BuildWeek2Team1.AziendaDiEnergia.security.JWTTtools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private JWTTtools jwtTtools;

    public TokenResponseDTO authenticateUser(AuthRequestDTO body){
        Utente utente=  utenteService.findByEmail(body.email());
        if (bcrypt.matches(body.password(), utente.getPassword())) {
            return new TokenResponseDTO(jwtTtools.createToken(utente));
        } else {
            throw new UnauthorizedException("Credenziali non valide!");
        }
    }

    public UtenteRespondDto save(UtenteRequestDto body){
        Utente utente = new Utente();
        Optional<Utente> checkEmail= utenteRepository.findByEmail(body.email());
        if(checkEmail.isEmpty()){
        utente.setUsername(body.username());
        utente.setEmail(body.email());
        utente.setPassword(bcrypt.encode(body.password()));
        utente.setNome(body.nome());
        utente.setCognome(body.cognome());
        utente.setAvatar(("https://ui-avatars.com/api/?name=" + body.nome() + "+" + body.cognome()));
        utente.setRuolo(Role.USER);
        utenteRepository.save(utente);
        return new UtenteRespondDto(utente.getUuid(),utente.getUsername(), utente.getEmail());
        }else{
            throw new EmailAlreadyInDbException(body.email());
        }
    }

    public UtenteRespondDto put(UtenteRequestDto body, UUID uuid){
        Optional<Utente> checkEmail= utenteRepository.findByEmail(body.email());

        if(checkEmail.isEmpty()
                ||
                utenteService.findByUUID(uuid).getEmail().equals(body.email())
        ){
            Utente utente = utenteService.findByUUID(uuid);
            utente.setUsername(body.username());
            utente.setEmail(body.email());
            utente.setPassword(bcrypt.encode(body.password()));
            utente.setNome(body.nome());
            utente.setCognome(body.cognome());
            utente.setAvatar(("https://ui-avatars.com/api/?name=" + body.nome() + "+" + body.cognome()));
            utente.setRuolo(utente.getRuolo());
            utenteRepository.save(utente);
            return new UtenteRespondDto(utente.getUuid(),utente.getUsername(), utente.getEmail());
        }
        else{
            throw new EmailAlreadyInDbException(body.email());
        }
    }
}
