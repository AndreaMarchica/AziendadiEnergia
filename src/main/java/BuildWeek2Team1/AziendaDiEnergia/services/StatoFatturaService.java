
package BuildWeek2Team1.AziendaDiEnergia.services;

import BuildWeek2Team1.AziendaDiEnergia.entities.StatoFatturaa;
import BuildWeek2Team1.AziendaDiEnergia.entities.StatoFatturaa;
import BuildWeek2Team1.AziendaDiEnergia.payloads.StatoFatturaDTO;
import BuildWeek2Team1.AziendaDiEnergia.repositories.StatoFatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatoFatturaService {

    @Autowired
    StatoFatturaRepository statoFatturaRepository;

    public StatoFatturaa save(StatoFatturaDTO body) {
        StatoFatturaa statoFattura = new StatoFatturaa();
        statoFattura.setStato(body.stato());
        try {
            return statoFatturaRepository.save(statoFattura);
        } catch (Exception e) {
            throw new RuntimeException("valore dello stato " + body.stato() + " già presente nel database");
        }

    }

    public List<StatoFatturaa> findAll() {
        return statoFatturaRepository.findAll();
    }

    public StatoFatturaa findByStato(String stato) {
        return statoFatturaRepository.findByStato(stato).orElseThrow(() -> new RuntimeException("Stato " + stato + " non trovato"));
    }
}