package BuildWeek2Team1.AziendaDiEnergia.runner;

import BuildWeek2Team1.AziendaDiEnergia.Service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ProvinceRunner implements CommandLineRunner {
    private final ProvinceService provinceService;

    @Autowired
    public ProvinceRunner(ProvinceService provinceService) {
        this.provinceService = provinceService;

    }
    @Override
    public void run(String... args) {
        String csvFilePath = "src/cvs/province-italiane.csv";
        provinceService.insertDataFromCsv(csvFilePath);
    }
}
