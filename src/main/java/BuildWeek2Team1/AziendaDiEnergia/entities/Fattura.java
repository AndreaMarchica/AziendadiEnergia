package BuildWeek2Team1.AziendaDiEnergia.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "fatture")
public class Fattura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
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
    private StatoFattura statoFattura;

}