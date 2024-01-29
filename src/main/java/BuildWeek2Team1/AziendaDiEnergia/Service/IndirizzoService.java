package BuildWeek2Team1.AziendaDiEnergia.Service;

import BuildWeek2Team1.AziendaDiEnergia.entities.Indirizzo;
import BuildWeek2Team1.AziendaDiEnergia.repositories.IndirizzoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class IndirizzoService {
    private final IndirizzoRepo indirizzoRepo;

    @Autowired
    public IndirizzoService(IndirizzoRepo indirizzoRepo) {
        this.indirizzoRepo = indirizzoRepo;
    }

    public List<Indirizzo> getAllIndirizzi() {
        return indirizzoRepo.findAll();
    }

    public Indirizzo getIndirizzoById(UUID id) {
        return indirizzoRepo.findById(id).orElse(null);
    }

    public void saveIndirizzo(Indirizzo indirizzo) {
        indirizzoRepo.save(indirizzo);
    }

    public void deleteIndirizzo(UUID id) {
        indirizzoRepo.deleteById(id);
    }
}