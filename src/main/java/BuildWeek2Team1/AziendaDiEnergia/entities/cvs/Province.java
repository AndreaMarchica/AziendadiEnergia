package BuildWeek2Team1.AziendaDiEnergia.entities.cvs;

        import jakarta.persistence.*;
        import lombok.Getter;
        import lombok.Setter;
        import lombok.ToString;

        import java.io.BufferedReader;
        import java.io.FileReader;
        import java.io.IOException;
        import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "province")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String sigla;
    private String provincia;
    private String regione;

}
