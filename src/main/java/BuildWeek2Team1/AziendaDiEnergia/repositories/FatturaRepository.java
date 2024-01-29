package BuildWeek2Team1.AziendaDiEnergia.repositories;

import BuildWeek2Team1.AziendaDiEnergia.entities.Fattura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, UUID> {
    Page<Fattura> findByImportoGreaterThan(double importo, Pageable pageable);

    Page<Fattura> findByImportoLessThan(double importo, Pageable pageable);

    Page<Fattura> findByData(LocalDate data, Pageable pageable);

    Page<Fattura> findByAnno(int anno, Pageable pageable);

    Page<Fattura> findByClienteId(UUID clienteId, Pageable pageable);

}