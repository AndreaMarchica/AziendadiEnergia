package BuildWeek2Team1.AziendaDiEnergia.controllers;

import BuildWeek2Team1.AziendaDiEnergia.entities.StatoFattura;
import BuildWeek2Team1.AziendaDiEnergia.exceptions.BadRequestException;
import BuildWeek2Team1.AziendaDiEnergia.payloads.StatoFatturaDTO;
import BuildWeek2Team1.AziendaDiEnergia.services.StatoFatturaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stati-fattura")
@Tag(name = "Stati fattura", description = "API gestione stati fattura")
public class StatoFatturaController {


    @Autowired
    StatoFatturaService statoFatturaService;

    @GetMapping("")
    public List<StatoFattura> findAll() {
        return statoFatturaService.findAll();
    }

    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public StatoFattura save(@RequestBody @Validated StatoFatturaDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            return statoFatturaService.save(body);
        }
    }

}