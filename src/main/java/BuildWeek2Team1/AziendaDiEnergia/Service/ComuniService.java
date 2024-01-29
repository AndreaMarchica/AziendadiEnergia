package BuildWeek2Team1.AziendaDiEnergia.Service;

import BuildWeek2Team1.AziendaDiEnergia.entities.cvs.Comuni;
import BuildWeek2Team1.AziendaDiEnergia.repositories.ComuniRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class ComuniService {
    private final ComuniRepository comuniRepository;
    @Autowired
    public ComuniService(ComuniRepository comuniRepository){
        this.comuniRepository = comuniRepository;
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

                    comuniRepository.save(comuni);
                } else {
                    System.out.println("Errore con  " + line);
                }
            }
        }catch (IOException e) {
            throw new RuntimeException("Errore nella lettura del file CSV" , e);
        }
    }

}
