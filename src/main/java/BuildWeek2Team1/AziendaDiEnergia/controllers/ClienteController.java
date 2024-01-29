package BuildWeek2Team1.AziendaDiEnergia.controllers;

import BuildWeek2Team1.AziendaDiEnergia.entities.Cliente;
import BuildWeek2Team1.AziendaDiEnergia.exceptions.BadRequestException;
import BuildWeek2Team1.AziendaDiEnergia.payloads.clienti.NewClienteDTO;
import BuildWeek2Team1.AziendaDiEnergia.payloads.clienti.NewClienteResponseDTO;
import BuildWeek2Team1.AziendaDiEnergia.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/clienti")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN', 'USER')")
    public Page<Cliente> getClienti(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "id") String sortBy){
        return clienteService.getClienti(page, size, sortBy);
    }
    @GetMapping("/{clienteId}")
    @PreAuthorize("hasAuthority('ADMIN', 'USER')")
    public Cliente findById(@PathVariable UUID clienteId) {
        return clienteService.findById(clienteId);
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN', 'USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public NewClienteResponseDTO saveCliente(@RequestBody @Validated NewClienteDTO body, BindingResult validation) throws Exception {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        Cliente newCliente = clienteService.save(body);
        return new NewClienteResponseDTO(newCliente.getId());
    }
    @PutMapping("/{clienteId}")
    @PreAuthorize("hasAuthority('ADMIN', 'USER')")
    public Cliente findAndUpdate(@PathVariable UUID clienteId, @RequestBody Cliente body) {
        return clienteService.findByIdAndUpdate(clienteId, body);
    }
    @DeleteMapping("/{clienteId}")
    @PreAuthorize("hasAuthority('ADMIN', 'USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable UUID clienteId) {
        clienteService.findByIdAndDelete(clienteId);
    }
}
