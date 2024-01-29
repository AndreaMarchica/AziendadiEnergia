package BuildWeek2Team1.AziendaDiEnergia.services;

import BuildWeek2Team1.AziendaDiEnergia.entities.Cliente;
import BuildWeek2Team1.AziendaDiEnergia.exceptions.NotFoundException;
import BuildWeek2Team1.AziendaDiEnergia.payloads.clienti.NewClienteDTO;
import BuildWeek2Team1.AziendaDiEnergia.payloads.clienti.NewClienteResponseDTO;
import BuildWeek2Team1.AziendaDiEnergia.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    public Cliente save(NewClienteDTO body){
        Cliente cliente = new Cliente();
        cliente.setRagioneSociale(body.ragioneSociale());
        cliente.setPartitaIva(body.partitaIva());
        cliente.setEmail(body.email());
        cliente.setDataInserimento(LocalDate.now());
        cliente.setDataUltimoContatto(body.dataUltimoContatto());
        cliente.setFatturatoAnnuale(body.fatturatoAnnuale());
        cliente.setPec(body.pec());
        cliente.setTelefono(body.telefono());
        cliente.setEmailContatto(body.emailContatto());
        cliente.setNomeContatto(body.nomeContatto());
        cliente.setCognomeContatto(body.cognomeContatto());
        cliente.setTelefonoContatto(body.telefonoContatto());
        cliente.setLogoAziendale(body.logoAziendale());
        return clienteRepository.save(cliente);
    }

    public Page<Cliente> getClienti(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return clienteRepository.findAll(pageable);
    }
    public Cliente findById(UUID id) {
        return clienteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
    public void findByIdAndDelete(UUID id){
        Cliente found = this.findById(id);
        clienteRepository.delete(found);
    }
    public Cliente findByIdAndUpdate(UUID id, Cliente body) {

        Cliente found = this.findById(id);
        found.setRagioneSociale(body.getRagioneSociale());
        found.setEmail(body.getEmail());
        found.setPartitaIva(body.getPartitaIva());
        found.setEmail(body.getEmail());
        found.setDataUltimoContatto(body.getDataUltimoContatto());
        found.setFatturatoAnnuale(body.getFatturatoAnnuale());
        found.setPec(body.getPec());
        found.setTelefono(body.getTelefono());
        found.setEmailContatto(body.getEmailContatto());
        found.setNomeContatto(body.getNomeContatto());
        found.setCognomeContatto(body.getCognomeContatto());
        found.setTelefonoContatto(body.getTelefonoContatto());
        found.setLogoAziendale(body.getLogoAziendale());
        return clienteRepository.save(found);
    }
    public Cliente findByEmail(String email) throws NotFoundException {
        return clienteRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Cliente con email " + email + " non trovata!"));
    }
}
