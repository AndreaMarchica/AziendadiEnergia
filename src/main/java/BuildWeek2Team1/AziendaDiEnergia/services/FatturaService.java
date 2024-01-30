package BuildWeek2Team1.AziendaDiEnergia.services;

import BuildWeek2Team1.AziendaDiEnergia.entities.Cliente;
import BuildWeek2Team1.AziendaDiEnergia.entities.Fattura;
import BuildWeek2Team1.AziendaDiEnergia.entities.StatoFatturaa;
import BuildWeek2Team1.AziendaDiEnergia.exceptions.NotFoundException;
import BuildWeek2Team1.AziendaDiEnergia.payloads.FatturaPostDTO;
import BuildWeek2Team1.AziendaDiEnergia.payloads.FatturaPutDTO;
import BuildWeek2Team1.AziendaDiEnergia.repositories.ClienteRepository;
import BuildWeek2Team1.AziendaDiEnergia.repositories.FatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FatturaService {
    @Autowired
    FatturaRepository fatturaRepository;
    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ClienteService clienteService;

  @Autowired
    StatoFatturaService statoFatturaService;

    public Fattura save(FatturaPostDTO body) {
        Cliente cliente = clienteService.findById(body.clienteId());
        Fattura newFattura = new Fattura();
        StatoFatturaa statoFattura = statoFatturaService.findByStato("DA_APPROVARE");
        newFattura.setNumeroFattura(body.numeroFattura());
        newFattura.setImporto(body.importo());
        newFattura.setData(LocalDate.now());
        newFattura.setCliente(cliente);
        newFattura.setAnno(LocalDate.now().getYear());
        newFattura.setStatoFattura(statoFattura);
        return fatturaRepository.save(newFattura);
    }


    public Fattura findById(UUID id) {

        return fatturaRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

    }

   public Fattura findAndUpdateById(UUID idNumero, FatturaPutDTO body) {
        Fattura fattura = this.findById(idNumero);
        StatoFatturaa statoFattura = statoFatturaService.findByStato(body.statoFattura());
        Cliente cliente = clienteService.findById(body.clienteId());
        fattura.setImporto(body.importo());
        fattura.setNumeroFattura(body.numeroFattura());
        fattura.setStatoFattura(statoFattura);
        fattura.setCliente(cliente);
        fatturaRepository.save(fattura);
        return fattura;
    }


    public Page<Fattura> getFatture(double importoGreater, double importoLess, String data, String statoFattura, int anno, UUID clientId, int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        List<Fattura> fatture = fatturaRepository.findAll();
        if (importoGreater != 0) {
            fatture = fatture.stream()
                    .filter(f -> f.getImporto() < importoGreater)
                    .collect(Collectors.toList());
        }

        if (importoLess != 0) {
            fatture = fatture.stream()
                    .filter(f -> f.getImporto() > importoLess)
                    .collect(Collectors.toList());
        }

        if (!data.isEmpty()) {
            fatture = fatture.stream()
                    .filter(f -> f.getData().equals(LocalDate.parse(data)))
                    .collect(Collectors.toList());
        }


       if (!statoFattura.isEmpty()) {
            fatture = fatture.stream()
                    .filter(f -> f.getStatoFattura().getStato().equals(statoFattura))
                    .collect(Collectors.toList());
        }
        if (anno != 0) {
            fatture = fatture.stream()
                    .filter(f -> f.getAnno() == anno)
                    .collect(Collectors.toList());
        }
        if (clientId != null) {
            fatture = fatture.stream()
                    .filter(f -> f.getCliente().getId() == clientId)
                    .collect(Collectors.toList());
        }


        return new PageImpl<>(fatture, pageable, fatture.size());
    }


    public void findAndDeleteByIdNumero(UUID id) {
        Fattura foundF = this.findById(id);
        fatturaRepository.delete(foundF);
    }
}