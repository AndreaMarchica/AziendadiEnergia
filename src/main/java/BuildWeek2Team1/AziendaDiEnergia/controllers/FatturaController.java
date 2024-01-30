package BuildWeek2Team1.AziendaDiEnergia.controllers;

import BuildWeek2Team1.AziendaDiEnergia.entities.Fattura;
import BuildWeek2Team1.AziendaDiEnergia.exceptions.BadRequestException;
import BuildWeek2Team1.AziendaDiEnergia.payloads.FatturaPostDTO;
import BuildWeek2Team1.AziendaDiEnergia.services.ClienteService;
import BuildWeek2Team1.AziendaDiEnergia.services.FatturaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fatture")
@Tag(name = "Fatture", description = "API gestione fatture")
public class FatturaController {
    @Autowired
    private FatturaService fatturaService;
    @Autowired
    private ClienteService clienteService;


    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Fattura saveFattura(@RequestBody @Validated FatturaPostDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            return fatturaService.save(body);
        }
    }

    @GetMapping("")
    public Page<Fattura> getFatturaByFiltro(

            @RequestParam(defaultValue = "") String statoFattura,
            @RequestParam(defaultValue = "") String data,
            @RequestParam(defaultValue = "0") int anno,
            @RequestParam(defaultValue = "0") long clientId,
            @RequestParam(defaultValue = "0") double importoMin,
            @RequestParam(defaultValue = "0") double importoMax,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String orderBy) {
        return fatturaService.getFatture(importoMax, importoMin, data, statoFattura, anno, clientId, page, size, orderBy);
    }

    @GetMapping("/{idNumero}")
    public Fattura findByIdNumero(@PathVariable long idNumero) {
        return fatturaService.findById(idNumero);
    }

    @PutMapping("/{idNumero}")
    public Fattura findAndUpdateById(@PathVariable long idNumero, @RequestBody @Validated FatturaPutDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            return fatturaService.findAndUpdateById(idNumero, body);
        }
    }

    @DeleteMapping("/{idNumero}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findAndDeleteByIdNumero(@PathVariable long idNumero) {
        fatturaService.findAndDeleteByIdNumero(idNumero);
    }
}