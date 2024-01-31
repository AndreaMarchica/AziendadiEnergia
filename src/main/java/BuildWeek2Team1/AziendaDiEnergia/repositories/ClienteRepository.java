package BuildWeek2Team1.AziendaDiEnergia.repositories;

import BuildWeek2Team1.AziendaDiEnergia.entities.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    Optional<Cliente> findByEmail (String email);
    Set<Cliente> findByFatturatoAnnualeBetween(long minimo, long massimo);
    Set<Cliente> findByDataInserimento(LocalDate dataInserimento);
    Set<Cliente> findByDataUltimoContatto(LocalDate dataUltimoContatto);
    Set<Cliente> findByNomeContattoContainingIgnoreCase(String nomeContatto);
}
