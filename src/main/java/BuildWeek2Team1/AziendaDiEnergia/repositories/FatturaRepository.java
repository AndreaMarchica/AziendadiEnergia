package BuildWeek2Team1.AziendaDiEnergia.repositories;

import BuildWeek2Team1.AziendaDiEnergia.entities.Fattura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FatturaRepository extends JpaRepository<Fattura, Long> {
}