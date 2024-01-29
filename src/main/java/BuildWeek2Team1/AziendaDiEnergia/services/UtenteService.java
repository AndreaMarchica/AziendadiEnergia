package BuildWeek2Team1.AziendaDiEnergia.services;

import BuildWeek2Team1.AziendaDiEnergia.entities.Utente;
import BuildWeek2Team1.AziendaDiEnergia.exceptions.EmailAlreadyInDbException;
import BuildWeek2Team1.AziendaDiEnergia.exceptions.ItemNotFoundException;
import BuildWeek2Team1.AziendaDiEnergia.payloads.UtentePayloads.UtenteRequestDto;
import BuildWeek2Team1.AziendaDiEnergia.payloads.UtentePayloads.UtenteRespondDto;
import BuildWeek2Team1.AziendaDiEnergia.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteDao;
    @Autowired
    private PasswordEncoder bcrypt;



    public List<Utente> getAll(){
        return utenteDao.findAll();
    }

    public UtenteRespondDto save(UtenteRequestDto body){
        Utente utente = new Utente();
        utente.setUsername(body.username());
        utente.setEmail(body.email());
        utente.setPassword(bcrypt.encode(body.password()));
        utente.setNome(body.nome());
        utente.setCognome(body.cognome());
        utente.setAvatar(("https://ui-avatars.com/api/?name=" + body.nome() + "+" + body.cognome()));
        utenteDao.save(utente);
        return new UtenteRespondDto(utente.getUuid(),utente.getUsername(), utente.getEmail());
    }

    public Utente findByUUID(UUID uuid){
        return utenteDao.findById(uuid).orElseThrow(()->new ItemNotFoundException(uuid));
    }

    public void delete (UUID uuid){
        utenteDao.delete(this.findByUUID(uuid));
    }

    public UtenteRespondDto put(UtenteRequestDto body, UUID uuid){
        Optional<Utente> checkEmail= utenteDao.findByEmail(body.email());
        if(checkEmail.isEmpty()){
        Utente utente = this.findByUUID(uuid);
        utente.setUsername(body.username());
        utente.setEmail(body.email());
        utente.setPassword(bcrypt.encode(body.password()));
        utente.setNome(body.nome());
        utente.setCognome(body.cognome());
        utente.setAvatar(("https://ui-avatars.com/api/?name=" + body.nome() + "+" + body.cognome()));
        utenteDao.save(utente);
        return new UtenteRespondDto(utente.getUuid(),utente.getUsername(), utente.getEmail());
        }else{
            throw new EmailAlreadyInDbException(body.email());
        }
    }

    public Utente findByEmail(String email){
        return utenteDao.findByEmail(email).orElseThrow(()-> new ItemNotFoundException(email));
    }
}
