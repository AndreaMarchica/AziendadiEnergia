package BuildWeek2Team1.AziendaDiEnergia.controllers;


import BuildWeek2Team1.AziendaDiEnergia.entities.Utente;
import BuildWeek2Team1.AziendaDiEnergia.services.UtenteService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/utenti")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Utente> findAll(){
        return utenteService.getAll();
    }

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Utente findById(@PathVariable UUID uuid){
        return utenteService.findByUUID(uuid);
    }
    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID uuid){
        utenteService.delete(uuid);
    }

}
