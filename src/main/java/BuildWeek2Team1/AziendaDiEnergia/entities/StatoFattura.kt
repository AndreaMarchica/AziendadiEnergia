package BuildWeek2Team1.AziendaDiEnergia.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter


@Entity
@Table(name = "stati_fattura")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
class StatoFattura {
    @Column(unique = true)
    var stato: String? = null

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long = 0

    @OneToMany(mappedBy = "statoFattura")
    @JsonIgnore
    private val fatture: List<Fattura>? = null
}