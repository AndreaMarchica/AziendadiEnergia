package BuildWeek2Team1.AziendaDiEnergia.runner;


import BuildWeek2Team1.AziendaDiEnergia.Service.IndirizzoService;
import BuildWeek2Team1.AziendaDiEnergia.entities.Indirizzo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class IndirizzoRunner implements CommandLineRunner {

    private final IndirizzoService indirizzoService;

    @Autowired
    public IndirizzoRunner(IndirizzoService indirizzoService) {
        this.indirizzoService = indirizzoService;
    }

    @Override
    public void run(String... args) {
        Indirizzo indirizzo1 = new Indirizzo();
        indirizzo1.setId(UUID.randomUUID());
        indirizzo1.setAdress("Indirizzo1");
        indirizzo1.setCivico(123);
        indirizzo1.setLocalita("Rogno");

        Indirizzo indirizzo2 = new Indirizzo();
        indirizzo2.setId(UUID.randomUUID());
        indirizzo2.setAdress("Indirizzo2");
        indirizzo2.setCivico(321);
        indirizzo2.setLocalita("Comazzo");

        try {
            indirizzoService.saveIndirizzo(indirizzo1);
            System.out.println("Indirizzo 1 salvato.");
        } catch (Exception e) {
            System.out.println("Error Indirizzo 1: " + e.getMessage());
        }

        try {
            indirizzoService.saveIndirizzo(indirizzo2);
            System.out.println("Indirizzo 2  salvato");
        } catch (Exception e) {
            System.out.println("ErrorIndirizzo 2: " + e.getMessage());
        }
    }
}