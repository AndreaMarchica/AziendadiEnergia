package BuildWeek2Team1.AziendaDiEnergia.services;

import BuildWeek2Team1.AziendaDiEnergia.entities.Role;
import BuildWeek2Team1.AziendaDiEnergia.entities.Utente;
import BuildWeek2Team1.AziendaDiEnergia.exceptions.EmailAlreadyInDbException;
import BuildWeek2Team1.AziendaDiEnergia.exceptions.NotFoundException;
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




    public List<Utente> getAll(){
        return utenteDao.findAll();
    }



    public Utente findByUUID(UUID uuid){
        return utenteDao.findById(uuid).orElseThrow(()->new NotFoundException(uuid));
    }

    public void delete (UUID uuid){
        utenteDao.delete(this.findByUUID(uuid));
    }


    public Utente findByEmail(String email){
        return utenteDao.findByEmail(email).orElseThrow(()-> new NotFoundException(email));
    }
}
