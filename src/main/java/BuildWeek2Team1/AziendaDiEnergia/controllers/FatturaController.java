package BuildWeek2Team1.AziendaDiEnergia.controllers;

import BuildWeek2Team1.AziendaDiEnergia.entities.Fattura;
import BuildWeek2Team1.AziendaDiEnergia.payloads.FatturaDTO;
import BuildWeek2Team1.AziendaDiEnergia.services.FatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fatture")
public class FatturaController {
    @Autowired
    private FatturaService fatturaService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Fattura saveFattura(@RequestBody FatturaDTO body){
        return fatturaService.save(body);
    }

    @GetMapping("")
    public Page<Fattura> getFattura(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "id") String orderBy) {
        return fatturaService.getFattura(page, size, orderBy);
    }

    @GetMapping("/{idNumero}")
    public Fattura findByIdNumero(@PathVariable long idNumero) {
        return fatturaService.findByIdNumero(idNumero);
    }
}