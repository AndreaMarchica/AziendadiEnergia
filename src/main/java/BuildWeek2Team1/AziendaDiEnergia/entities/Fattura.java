package BuildWeek2Team1.AziendaDiEnergia.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Fattura {

    @Id
    @GeneratedValue
    private UUID id;
    @Column(name = "numero_fattura")
    private String numeroFattura;
    private double importo;
    private LocalDate data;
    private int anno;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

   @ManyToOne
    @JoinColumn(name = "stato_fattura_id", unique = false)
    private StatoFatturaa statoFattura;

}