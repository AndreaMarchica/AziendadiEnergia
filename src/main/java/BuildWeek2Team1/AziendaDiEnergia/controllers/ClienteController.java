package BuildWeek2Team1.AziendaDiEnergia.controllers;

import BuildWeek2Team1.AziendaDiEnergia.config.EmailSender;
import BuildWeek2Team1.AziendaDiEnergia.entities.Cliente;
import BuildWeek2Team1.AziendaDiEnergia.entities.Utente;
import BuildWeek2Team1.AziendaDiEnergia.exceptions.BadRequestException;
import BuildWeek2Team1.AziendaDiEnergia.payloads.clienti.NewClienteDTO;
import BuildWeek2Team1.AziendaDiEnergia.payloads.clienti.NewClienteResponseDTO;
import BuildWeek2Team1.AziendaDiEnergia.repositories.ClienteRepository;
import BuildWeek2Team1.AziendaDiEnergia.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/clienti")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @Autowired
     EmailSender emailSender;
    @Autowired
    private ClienteRepository clienteRepository;


    @GetMapping("")
    public Page<Cliente> getClienti(
            @RequestParam(defaultValue = "*") String nomeContatto,
            @RequestParam(defaultValue = "") LocalDate dataUltimoContatto,
            @RequestParam(defaultValue = "") LocalDate dataInserimento,
            @RequestParam(defaultValue = "0") long minimo,
            @RequestParam(defaultValue = "99999999") long massimo,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

/*        if (minimo > 0 || massimo < 99999999) {
            Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(sortBy));
            return clienteService.getClientiByFatturatoAnnuale(minimo, massimo, pageable);
        } else if (dataInserimento != null) {
            Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(sortBy));
            return clienteService.getClientiByDataInserimento(dataInserimento);
        } else if (dataUltimoContatto != null) {
            Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(sortBy));
            return clienteService.getClientiByDataUltimoContatto(dataUltimoContatto);
        } else if (nomeContatto != null) {
            Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(sortBy));
            return clienteService.getClientiByNomeContattoContaining(nomeContatto);
        } else return clienteService.getClienti(pageNumber, size, sortBy);*/
        return  clienteService.getClienti2(nomeContatto, dataUltimoContatto, dataInserimento, minimo, massimo, pageNumber, size, sortBy);
    }


    @GetMapping("/{clienteId}")
    public Cliente findById(@PathVariable UUID clienteId) {
        return clienteService.findById(clienteId);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public NewClienteResponseDTO saveCliente(@RequestBody @Validated NewClienteDTO body, BindingResult validation) throws Exception {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
//        emailSender.sendEmail("lorebalda93@gmail.com");
        emailSender.sendEmail(body.email());
        Cliente newCliente = clienteService.save(body);
        return new NewClienteResponseDTO(newCliente.getId());
    }

    @PutMapping("/{clienteId}")
    public Cliente findAndUpdate(@PathVariable UUID clienteId, @RequestBody NewClienteDTO body) {
        return clienteService.findByIdAndUpdate(clienteId, body);
    }

    @DeleteMapping("/{clienteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable UUID clienteId) {
        clienteService.findByIdAndDelete(clienteId);
    }

    @PatchMapping("/{uuid}/upload")
    public String uploadEventImageByAdmin(@RequestParam("avatar") MultipartFile file,@PathVariable UUID uuid) throws IOException {
        String url = clienteService.uploadPicture(file);
        Cliente cliente = clienteService.findById(uuid);
        cliente.setLogoAziendale(url);
        clienteRepository.save(cliente);
        return "ok immagine salvata " + url;
    }
}