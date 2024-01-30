package BuildWeek2Team1.AziendaDiEnergia.Service;

import BuildWeek2Team1.AziendaDiEnergia.entities.Indirizzo;
import BuildWeek2Team1.AziendaDiEnergia.entities.cvs.Comuni;
import BuildWeek2Team1.AziendaDiEnergia.entities.cvs.Province;
import BuildWeek2Team1.AziendaDiEnergia.repositories.ComuniRepository;
import BuildWeek2Team1.AziendaDiEnergia.repositories.IndirizzoRepo;
import BuildWeek2Team1.AziendaDiEnergia.repositories.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class IndirizzoService {
    private final IndirizzoRepo indirizzoRepo;
    private final ComuniRepository comuniRepository;
    private final ProvinceRepository provinceRepository;

    @Autowired
    public IndirizzoService(IndirizzoRepo indirizzoRepo, ComuniRepository comuniRepository, ProvinceRepository provinceRepository) {
        this.indirizzoRepo = indirizzoRepo;
        this.comuniRepository = comuniRepository;
        this.provinceRepository = provinceRepository;
    }

    public void saveIndirizzo(Indirizzo indirizzo) {
        String localita = indirizzo.getLocalita();
        Optional<Comuni> comuniOptional = comuniRepository.findByDenominazione(localita);
        if (comuniOptional.isPresent()) {
            Comuni comuni = comuniOptional.get();
            Optional<Province> provinceOptional = provinceRepository.findByProvincia(comuni.getProvincia());
            if (provinceOptional.isPresent()) {
                Province province = provinceOptional.get();
                indirizzo.setRegione(province.getRegione());
                indirizzo.setSigla(province.getSigla());
                indirizzo.setComuni(comuni);
                indirizzo.setCodiceProvincia(comuni.getCodice_Provincia());
                indirizzo.setProgressivo(comuni.getProgressivo_del_Comune());
                indirizzo.setProvincia(comuni.getProvincia());
                indirizzoRepo.save(indirizzo);
            } else {
                System.out.println("Province non trovata per  Comune: " + comuni.getDenominazione());
            }
        } else {
            System.out.println("Comuni not found for Localita: " + localita);
        }
    }
    public List<Indirizzo> getAllIndirizzi() {
        return indirizzoRepo.findAll();
    }

    public Indirizzo getIndirizzoById(UUID id) {
        return indirizzoRepo.findById(id).orElse(null);
    }



    public void deleteIndirizzo(UUID id) {
        indirizzoRepo.deleteById(id);
    }
}