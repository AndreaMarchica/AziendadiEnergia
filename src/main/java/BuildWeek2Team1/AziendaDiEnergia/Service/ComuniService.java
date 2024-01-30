package BuildWeek2Team1.AziendaDiEnergia.Service;

import BuildWeek2Team1.AziendaDiEnergia.entities.cvs.Comuni;
import BuildWeek2Team1.AziendaDiEnergia.entities.cvs.Province;
import BuildWeek2Team1.AziendaDiEnergia.repositories.ComuniRepository;
import BuildWeek2Team1.AziendaDiEnergia.repositories.ProvinceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

@Service
public class ComuniService {
    private final ComuniRepository comuniRepository;
    private final ProvinceRepository provinceRepository;
    @Autowired
    public ComuniService(ComuniRepository comuniRepository,  ProvinceRepository provinceRepository){

        this.comuniRepository = comuniRepository;
        this.provinceRepository = provinceRepository;
    }

    @Transactional
    public void  insertDataFromCsv2(String file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line ="";
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                Comuni comuni = new Comuni();

                if (data.length >= 4) {
                    comuni.setCodice_Provincia(data[0].trim());

                    comuni.setProgressivo_del_Comune(data[1].trim());

                    comuni.setDenominazione(data[2].trim());
                    comuni.setProvincia(data[3].trim());

                    Optional<Comuni> existingComuni = comuniRepository.findByDenominazione(comuni.getDenominazione());

                    if (existingComuni.isPresent()) {
                    } else {
                        Optional<Province> provinceOptional = provinceRepository.findBySigla(comuni.getCodice_Provincia());

                        if (provinceOptional.isPresent()) {
                            Province province = provinceOptional.get();
                            comuni.setRegione(province.getRegione());
                            comuni.setSigla(province.getSigla());
                        }
                        try {
                            comuniRepository.save(comuni);
                        } catch (Exception e) {
                            System.out.println("Error saving comune: " + comuni.getDenominazione() + ", Error: " + e.getMessage());
                        }
                    }
                } else {
                    System.out.println("Errore con  " + line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Errore nella lettura del file CSV", e);
        }
    }}
