package BuildWeek2Team1.AziendaDiEnergia.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Entity
@Setter
@Getter

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
    private String telefono;
    private String emailContatto;
    private  String nomeContatto;
    private  String cognomeContatto;
    private String telefonoContatto;
    private String logoAziendale;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "indirizzo1")
    private Indirizzo indirizzo1;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "indirizzo2")
    private Indirizzo indirizzo2;
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

    public void setTelefono(String telefono) {
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

    public void setTelefonoContatto(String telefonoContatto) {
        this.telefonoContatto = telefonoContatto;
    }

    public void setLogoAziendale(String logoAziendale) {
        this.logoAziendale = logoAziendale;
    }

    public void setIndirizzo1(Indirizzo indirizzo1) {
        this.indirizzo1 = indirizzo1;
    }

    public void setIndirizzo2(Indirizzo indirizzo2) {
        this.indirizzo2 = indirizzo2;
    }


    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", ragioneSociale=" + ragioneSociale +
                ", partitaIva='" + partitaIva + '\'' +
                ", email='" + email + '\'' +
                ", dataInserimento=" + dataInserimento +
                ", dataUltimoContatto=" + dataUltimoContatto +
                ", fatturatoAnnuale=" + fatturatoAnnuale +
                ", pec='" + pec + '\'' +
                ", telefono='" + telefono + '\'' +
                ", emailContatto='" + emailContatto + '\'' +
                ", nomeContatto='" + nomeContatto + '\'' +
                ", cognomeContatto='" + cognomeContatto + '\'' +
                ", telefonoContatto='" + telefonoContatto + '\'' +
                ", logoAziendale='" + logoAziendale + '\'' +
                '}';
    }
}
