package BuildWeek2Team1.AziendaDiEnergia.repositories;

import BuildWeek2Team1.AziendaDiEnergia.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    Optional<Cliente> findByEmail (String email);
}
