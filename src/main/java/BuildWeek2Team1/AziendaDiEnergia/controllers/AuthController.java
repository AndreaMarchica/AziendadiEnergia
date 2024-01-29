package BuildWeek2Team1.AziendaDiEnergia.controllers;

import BuildWeek2Team1.AziendaDiEnergia.exceptions.BadRequestException;
import BuildWeek2Team1.AziendaDiEnergia.payloads.AuthPayloads.AuthRequestDTO;
import BuildWeek2Team1.AziendaDiEnergia.payloads.UtentePayloads.UtenteRequestDto;
import BuildWeek2Team1.AziendaDiEnergia.payloads.UtentePayloads.UtenteRespondDto;
import BuildWeek2Team1.AziendaDiEnergia.services.AuthService;
import BuildWeek2Team1.AziendaDiEnergia.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;



    @PostMapping("/register")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public UtenteRespondDto register(@RequestBody @Validated UtenteRequestDto body,
                                     BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            System.err.println(bindingResult.getAllErrors());
            throw new BadRequestException("errore nel invio del payload per il metodo POST"+bindingResult.getAllErrors());
        } else {
        return authService.save(body);
        }
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String generateToken(@RequestBody AuthRequestDTO body){
        return authService.authenticateUser(body);
    }

}
