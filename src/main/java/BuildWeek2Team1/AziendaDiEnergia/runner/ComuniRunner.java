package BuildWeek2Team1.AziendaDiEnergia.runner;

import BuildWeek2Team1.AziendaDiEnergia.Service.ComuniService;
import BuildWeek2Team1.AziendaDiEnergia.Service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ComuniRunner implements CommandLineRunner {
    private final ComuniService comuniService;

    @Autowired
    public ComuniRunner(ComuniService comuniService) {
        this.comuniService = comuniService;

    }
    @Override
    public void run(String... args) {
        String csvFilePath = "src/cvs/comuni-italiani.csv";
        comuniService.insertDataFromCsv2(csvFilePath);
    }
}