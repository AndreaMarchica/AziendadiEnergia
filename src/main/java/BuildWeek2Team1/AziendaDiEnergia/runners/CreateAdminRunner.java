package BuildWeek2Team1.AziendaDiEnergia.runners;

import BuildWeek2Team1.AziendaDiEnergia.entities.Role;
import BuildWeek2Team1.AziendaDiEnergia.entities.Utente;
import BuildWeek2Team1.AziendaDiEnergia.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class CreateAdminRunner implements CommandLineRunner {


    @Autowired
    private PasswordEncoder bcrypt;
    @Autowired
    private UtenteRepository utenteRepository;

    @Override
    public void run(String... args) throws Exception {
        Optional<Utente> check = utenteRepository.findByEmail("admin1@gmail.com");
        if(check.isEmpty())
        {
        Utente utente= new Utente();
        utente.setUsername("admin1");
        utente.setEmail("admin1@gmail.com");
        utente.setPassword(bcrypt.encode("admin1"));
        utente.setNome("admin1");
        utente.setCognome("admin1");
        utente.setAvatar(("https://ui-avatars.com/api/?name=" + "admin1" + "+" + "admin1"));
        utente.setRuolo(Role.ADMIN);
        utenteRepository.save(utente);
        System.out.println("ok admin creato");
        }else{
            System.out.println("Dio ha gia creato l'admin");
        }
    }
}
