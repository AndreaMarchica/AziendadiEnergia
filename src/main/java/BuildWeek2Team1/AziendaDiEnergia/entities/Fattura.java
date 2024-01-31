package BuildWeek2Team1.AziendaDiEnergia.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Fattura {

    @Id
    @GeneratedValue
    @Column(name = "numero_fattura")
    private UUID numero_fattura;
    private double importo;
    private LocalDate data;
    private int anno;
    @Enumerated(EnumType.STRING)
    private StatoFattura statoFattura;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;


    @Override
    public String toString() {
        return "Fattura{" +
                "numero_fattura=" + numero_fattura +
                ", importo=" + importo +
                ", data=" + data +
                ", anno=" + anno +
                ", statoFattura=" + statoFattura +
                '}';
    }
}