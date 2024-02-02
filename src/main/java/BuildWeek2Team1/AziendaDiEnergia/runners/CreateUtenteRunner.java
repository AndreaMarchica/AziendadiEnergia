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
public class CreateUtenteRunner implements CommandLineRunner {


    @Autowired
    private PasswordEncoder bcrypt;
    @Autowired
    private UtenteRepository utenteRepository;

    @Override
    public void run(String... args) throws Exception {
        Optional<Utente> check = utenteRepository.findByEmail("utente1@gmail.com");
        if(check.isEmpty())
        {
        Utente utente= new Utente();
        utente.setUsername("utente1");
        utente.setEmail("utente1@gmail.com");
        utente.setPassword(bcrypt.encode("utente1"));
        utente.setNome("utenteNome");
        utente.setCognome("utenteCognome");
        utente.setAvatar(("https://ui-avatars.com/api/?name=" + utente.getNome() + "+" + utente.getCognome()));
        utente.setRuolo(Role.USER);
        utenteRepository.save(utente);
        System.out.println("ok Utente creato");
        }else{
            System.out.println("Dio ha gia creato l'utente");
        }
    }
}
