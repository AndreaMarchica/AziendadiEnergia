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
@Table(name = "comuni")
public class Comuni {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String Codice_Provincia;
    private String Progressivo_del_Comune;
    private String Denominazione_in_italiano;

}
