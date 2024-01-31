package BuildWeek2Team1.AziendaDiEnergia.repositories;

import BuildWeek2Team1.AziendaDiEnergia.entities.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    Optional<Cliente> findByEmail (String email);
    Page<Cliente> findByFatturatoAnnualeBetween(long minimo, long massimo, Pageable pageable);
    Page<Cliente> findByDataInserimento(LocalDate dataDiInserimento, Pageable pageable);
    Page<Cliente> findByDataUltimoContatto(LocalDate dataUltimoContatto, Pageable pageable);
    Page<Cliente> findByNomeContattoContainingIgnoreCase(String nomeContatto, Pageable pageable);
}
