package BuildWeek2Team1.AziendaDiEnergia.Service;


import BuildWeek2Team1.AziendaDiEnergia.entities.cvs.Province;
import BuildWeek2Team1.AziendaDiEnergia.repositories.ProvinceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

@Service
public class ProvinceService {

    private final ProvinceRepository provinceRepository;

    @Autowired
    public ProvinceService(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    @Transactional
    public void insertDataFromCsv(String csvFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                Province province = new Province();

                province.setSigla(data[0].trim());
                province.setProvincia(data[1].trim());
                province.setRegione(data[2].trim());
                Optional<Province> existingProvince = provinceRepository.findBySigla(province.getSigla());

                if (existingProvince.isPresent()) {
                } else {
                    try {
                        provinceRepository.save(province);
                    } catch (Exception e) {
                        System.out.println("Error saving province: " + province.getProvincia() + ", Error: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading CSV file", e);
        }
    }
}
