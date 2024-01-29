package BuildWeek2Team1.AziendaDiEnergia.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Fattura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_numero;

    private double importo;
    private LocalDate data;

}