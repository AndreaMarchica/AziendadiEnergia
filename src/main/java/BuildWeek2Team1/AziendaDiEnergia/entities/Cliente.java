package BuildWeek2Team1.AziendaDiEnergia.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Entity
@Getter
@ToString
public class Cliente {
    @Id
    @GeneratedValue
    private UUID id;
    @Enumerated(EnumType.STRING)
    private RagioneSociale ragioneSociale;
    private String partitaIva;
    private String email;
    private LocalDate dataInserimento;
    private LocalDate dataUltimoContatto;
    private double fatturatoAnnuale;
    private String pec;
    private long telefono;
    private String emailContatto;
    private  String nomeContatto;
    private  String cognomeContatto;
    private long telefonoContatto;
    private String logoAziendale;

    @OneToMany(mappedBy = "cliente",cascade = CascadeType.REMOVE)
    @JsonIgnore
    List<Indirizzo> indirizzi;
    @OneToMany(mappedBy = "cliente",cascade = CascadeType.REMOVE)
    @JsonIgnore
    List<Fattura> fatture;
//metodi
    public void setRagioneSociale(RagioneSociale ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    public void setPartitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDataInserimento(LocalDate dataInserimento) {
        this.dataInserimento = dataInserimento;
    }

    public void setDataUltimoContatto(LocalDate dataUltimoContatto) {
        this.dataUltimoContatto = dataUltimoContatto;
    }

    public void setFatturatoAnnuale(double fatturatoAnnuale) {
        this.fatturatoAnnuale = fatturatoAnnuale;
    }

    public void setPec(String pec) {
        this.pec = pec;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public void setEmailContatto(String emailContatto) {
        this.emailContatto = emailContatto;
    }

    public void setNomeContatto(String nomeContatto) {
        this.nomeContatto = nomeContatto;
    }

    public void setCognomeContatto(String cognomeContatto) {
        this.cognomeContatto = cognomeContatto;
    }

    public void setTelefonoContatto(long telefonoContatto) {
        this.telefonoContatto = telefonoContatto;
    }

    public void setLogoAziendale(String logoAziendale) {
        this.logoAziendale = logoAziendale;
    }
}
