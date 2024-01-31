package BuildWeek2Team1.AziendaDiEnergia.repositories;

import BuildWeek2Team1.AziendaDiEnergia.entities.cvs.Comuni;
import BuildWeek2Team1.AziendaDiEnergia.entities.cvs.Province;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProvinceRepository extends JpaRepository<Province, UUID> {
    Optional<Province> findBySigla(String sigla);
    Optional<Province> findByProvincia(String provincia);

}
