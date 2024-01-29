package BuildWeek2Team1.AziendaDiEnergia.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "stati_fattura")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StatoFattura {
    @Column(unique = true)
    String stato;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToMany(mappedBy = "statoFattura")
    @JsonIgnore
    private List<Fattura> fatture;
}