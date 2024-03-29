package BuildWeek2Team1.AziendaDiEnergia.repositories;

import BuildWeek2Team1.AziendaDiEnergia.entities.Indirizzo;
import BuildWeek2Team1.AziendaDiEnergia.entities.cvs.Comuni;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IndirizzoRepo extends JpaRepository<Indirizzo, UUID> {
    Optional<Indirizzo> findByAdressAndCapAndLocalita(String adress, long cap, String localita);
}
