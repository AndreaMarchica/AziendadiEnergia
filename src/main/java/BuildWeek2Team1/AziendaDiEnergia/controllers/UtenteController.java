package BuildWeek2Team1.AziendaDiEnergia.controllers;


import BuildWeek2Team1.AziendaDiEnergia.entities.Utente;
import BuildWeek2Team1.AziendaDiEnergia.exceptions.BadRequestException;
import BuildWeek2Team1.AziendaDiEnergia.payloads.UtentePayloads.UtenteRequestDto;
import BuildWeek2Team1.AziendaDiEnergia.payloads.UtentePayloads.UtenteRespondDto;
import BuildWeek2Team1.AziendaDiEnergia.repositories.UtenteRepository;
import BuildWeek2Team1.AziendaDiEnergia.services.AuthService;
import BuildWeek2Team1.AziendaDiEnergia.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/utenti")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;
    @Autowired
    private AuthService authService;
    @Autowired
    private UtenteRepository utenteRepository;


    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Utente> findAll(@RequestParam(defaultValue = "0")int page,
                                @RequestParam(defaultValue = "10")int size,
                                @RequestParam(defaultValue = "uuid")String ordetBy){
        return utenteService.getAll(page, size, ordetBy);
    }

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Utente findById(@PathVariable UUID uuid){
        return utenteService.findByUUID(uuid);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable UUID uuid){
        utenteService.delete(uuid);
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ADMIN')")
    public UtenteRespondDto modify(@RequestBody @Validated UtenteRequestDto body,
                                   @PathVariable UUID uuid, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            System.err.println(bindingResult.getAllErrors());
            throw new BadRequestException("errore nel invio del payload per il metodo POST"+bindingResult.getAllErrors());
        } else {
            return authService.put(body,uuid);
        }
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Utente findById(@AuthenticationPrincipal Utente currentUser){
        return currentUser;
    }



    @PutMapping("/me/upload")
    public String uploadEventImage(@RequestParam("avatar") MultipartFile file, @AuthenticationPrincipal Utente currentUser) throws IOException {
        String url = utenteService.uploadPicture(file);
        Utente utente = currentUser;
        utente.setUsername(utente.getUsername());
        utente.setEmail(utente.getEmail());
        utente.setPassword(utente.getPassword());
        utente.setNome(utente.getNome());
        utente.setCognome(utente.getCognome());
        utente.setAvatar(url);
        utenteRepository.save(utente);
        return "ok immagine salvata " + url;
    }
    @PatchMapping("/{uuid}/upload")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String uploadEventImageByAdmin(@RequestParam("avatar") MultipartFile file,@PathVariable UUID uuid) throws IOException {
        String url = utenteService.uploadPicture(file);
        Utente utente = utenteService.findByUUID(uuid);
        utente.setAvatar(url);
        utenteRepository.save(utente);
        return "ok immagine salvata " + url;
    }
}
