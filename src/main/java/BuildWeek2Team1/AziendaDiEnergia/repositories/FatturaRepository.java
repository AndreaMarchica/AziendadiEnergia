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
    List<Fattura> findByImportoBetween(double importoMin, double importoMax);


    List<Fattura> findByData(LocalDate data);

    List<Fattura> findByAnno(int anno );

    List<Fattura> findByClienteId(UUID clienteId);
    List<Fattura> findByStatoFattura(StatoFattura statoFattura);
    List<Fattura> findByStatoFatturaAndClienteIdAndAnnoAndDataAndImportoBetween(StatoFattura statoFattura,
                                                               UUID clienteI,int anno,LocalDate data,
                                                                                double importoMin, double importoMax);

}