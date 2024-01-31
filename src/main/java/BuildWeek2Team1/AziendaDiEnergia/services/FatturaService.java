package BuildWeek2Team1.AziendaDiEnergia.services;

import BuildWeek2Team1.AziendaDiEnergia.entities.Cliente;
import BuildWeek2Team1.AziendaDiEnergia.entities.Fattura;
import BuildWeek2Team1.AziendaDiEnergia.entities.StatoFattura;
import BuildWeek2Team1.AziendaDiEnergia.exceptions.NotFoundException;
import BuildWeek2Team1.AziendaDiEnergia.payloads.FatturaPayloads.FatturaPostDTO;
import BuildWeek2Team1.AziendaDiEnergia.payloads.FatturaPayloads.FatturaPutDTO;
import BuildWeek2Team1.AziendaDiEnergia.repositories.ClienteRepository;
import BuildWeek2Team1.AziendaDiEnergia.repositories.FatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FatturaService {
    @Autowired
    FatturaRepository fatturaRepository;
    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ClienteService clienteService;


    public Fattura save(FatturaPostDTO body) {
        Cliente cliente = clienteService.findById(body.clienteId());
        Fattura newFattura = new Fattura();
        newFattura.setStatoFattura(StatoFattura.DA_APPROVARE);
        newFattura.setImporto(body.importo());
        newFattura.setData(LocalDate.now());
        newFattura.setCliente(cliente);
        newFattura.setAnno(LocalDate.now().getYear());
        return fatturaRepository.save(newFattura);
    }


    public Fattura findById(UUID id) {

        return fatturaRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

    }

   public Fattura findAndUpdateById(UUID idNumero, FatturaPutDTO body) {
        Fattura fattura = this.findById(idNumero);
        Cliente cliente = clienteService.findById(body.clienteId());
        fattura.setImporto(body.importo());
        fattura.setStatoFattura(StatoFattura.valueOf(body.statoFattura()));
        fattura.setCliente(cliente);
        fatturaRepository.save(fattura);
        return fattura;
    }


    public Page<Fattura> getFatture(
            double importoGreater, double importoLess, LocalDate data,
                                    String statoFattura, int anno, UUID clientId,
                                    int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));

        Set<Fattura> fatture=  new HashSet<>();
//        Set<Fattura> fatture= fatturaRepository.findByStatoFatturaAndClienteIdAndAnnoAndDataAndImportoBetween(
//                ok,clientId,anno,data,importoLess,importoGreater);

        if (importoGreater != 0 && importoLess != 0) {
            Set<Fattura> lista1=fatturaRepository.findByImportoBetween(importoLess,importoGreater);
            fatture.addAll(lista1);
            System.out.println("importo");
            System.out.println(lista1);
        }

        if (data!= null) {
            Set<Fattura> lista1=fatturaRepository.findByData(data);
            fatture.addAll(lista1);
            System.out.println("data");

        }


       if (!statoFattura.isEmpty()) {
           StatoFattura ok= StatoFattura.valueOf(statoFattura);
           Set<Fattura> lista1=fatturaRepository.findByStatoFattura(ok);
           fatture.addAll(lista1);
           System.out.println("statofattura");

       }
        if (anno != 0) {
            Set<Fattura> lista1=fatturaRepository.findByAnno(anno);
            fatture.addAll(lista1);
            System.out.println("anno");

        }
        if (clientId != null) {
            Set<Fattura> lista1=fatturaRepository.findByClienteId(clientId);
            fatture.addAll(lista1);
            System.out.println("clientid");

        }

        //dopo

        System.out.println(fatture);

        if (importoGreater != 0 && importoLess != 0) {
           fatture = fatture.stream().filter(f->f.getImporto()>importoLess&&f.getImporto()<importoGreater).collect(Collectors.toSet());

            System.out.println(fatture);
        }


        if (data!= null) {

            fatture= fatture.stream().filter(f->f.getData()==data).collect(Collectors.toSet());
        }


        if (!statoFattura.isEmpty()) {
            fatture=fatture.stream().filter(f->f.getStatoFattura().equals(statoFattura)).collect(Collectors.toSet());
        }
        if (anno != 0) {

            fatture=fatture.stream().filter(f->f.getAnno()==anno).collect(Collectors.toSet());
        }
        if (clientId != null) {

            fatture=fatture.stream().filter(f->f.getCliente().equals(clientId)).collect(Collectors.toSet());

        }

        List<Fattura> filteredList = new ArrayList<>();
        filteredList.addAll(fatture);
        return new PageImpl<>(filteredList, pageable, fatture.size());
    }



    public void findAndDeleteByIdNumero(UUID id) {
        Fattura foundF = this.findById(id);
        fatturaRepository.delete(foundF);
    }
}