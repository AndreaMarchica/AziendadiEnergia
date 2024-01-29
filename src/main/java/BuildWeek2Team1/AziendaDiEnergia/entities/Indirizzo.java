package BuildWeek2Team1.AziendaDiEnergia.entities;

import BuildWeek2Team1.AziendaDiEnergia.entities.cvs.Comuni;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "indirizzo")
public class Indirizzo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String adress;
    private int civico;
    private String localita;
    private long cap;

    private Comuni comuni;
}
