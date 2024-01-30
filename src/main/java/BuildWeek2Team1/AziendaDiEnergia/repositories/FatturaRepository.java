package BuildWeek2Team1.AziendaDiEnergia.repositories;

import BuildWeek2Team1.AziendaDiEnergia.entities.Fattura;
import BuildWeek2Team1.AziendaDiEnergia.entities.StatoFattura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, UUID> {
    Set<Fattura> findByImportoBetween(double importoMin, double importoMax);


    Set<Fattura> findByData(LocalDate data);

    Set<Fattura> findByAnno(int anno );

    Set<Fattura> findByClienteId(UUID clienteId);
    Set<Fattura> findByStatoFattura(StatoFattura statoFattura);
    Set<Fattura> findByStatoFatturaAndClienteIdAndAnnoAndDataAndImportoBetween(StatoFattura statoFattura,
                                                               UUID clienteI,int anno,LocalDate data,
                                                                                double importoMin, double importoMax);

}