
package BuildWeek2Team1.AziendaDiEnergia.repositories;


import BuildWeek2Team1.AziendaDiEnergia.entities.StatoFatturaa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatoFatturaRepository extends JpaRepository<StatoFatturaa, Long> {

    public Optional<StatoFatturaa> findByStato(String stato);

    public Optional<StatoFatturaa> findById(long id);
}