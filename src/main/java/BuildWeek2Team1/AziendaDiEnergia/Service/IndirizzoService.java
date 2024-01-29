package BuildWeek2Team1.AziendaDiEnergia.Service;

import BuildWeek2Team1.AziendaDiEnergia.entities.Indirizzo;
import BuildWeek2Team1.AziendaDiEnergia.entities.cvs.Comuni;
import BuildWeek2Team1.AziendaDiEnergia.repositories.ComuniRepository;
import BuildWeek2Team1.AziendaDiEnergia.repositories.IndirizzoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class IndirizzoService {
    private final IndirizzoRepo indirizzoRepo;
    private final ComuniRepository comuniRepository;

    @Autowired
    public IndirizzoService(IndirizzoRepo indirizzoRepo, ComuniRepository comuniRepository) {
        this.indirizzoRepo = indirizzoRepo;
        this.comuniRepository = comuniRepository;
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