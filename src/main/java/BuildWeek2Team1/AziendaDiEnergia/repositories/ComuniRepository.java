package BuildWeek2Team1.AziendaDiEnergia.repositories;

import BuildWeek2Team1.AziendaDiEnergia.entities.cvs.Comuni;
import BuildWeek2Team1.AziendaDiEnergia.entities.cvs.Province;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ComuniRepository extends JpaRepository<Comuni, UUID> {

    Optional<Comuni> findByDenominazione(String denominazione);
}
