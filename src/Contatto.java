import java.io.Serializable;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

public class Contatto implements Serializable {
    private String nome;
    private String cognome;
    private String numeroTelefonico;
    private String indirizzo;
    private String citta;
    private String provincia;
    private String paese;
    private String cap;
    private String prefisso;

    private ZonedDateTime dataCreazione;//DEVE CREARSI AUTOMATICAMENTE QUANDO SI CREA IL NUOVO CONTATTO

    public Contatto(String nome, String cognome, String numeroTelefonico) {
        this.nome = nome;
        this.cognome = cognome;
        this.numeroTelefonico = numeroTelefonico;
        this.dataCreazione = OffsetDateTime.now().toZonedDateTime();
    }


    public Contatto(String nome, String cognome, String numeroTelefonico, String indirizzo, String citta, String provincia, String paese, String cap, String prefisso) {
        this.nome = nome;
        this.cognome = cognome;
        this.numeroTelefonico = numeroTelefonico;
        this.indirizzo = indirizzo;
        this.citta = citta;
        this.provincia = provincia;
        this.paese = paese;
        this.cap = cap;
        this.prefisso = prefisso;
        this.dataCreazione = OffsetDateTime.now().toZonedDateTime();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setNumeroTelefonico(String numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setPaese(String paese) {
        this.paese = paese;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public void setPrefisso(String prefisso) {
        this.prefisso = prefisso;
    }

    public String getCognome() {
        return cognome;
    }

    public String getNome() {
        return nome;
    }

    public String getNumeroTelefonico() {
        return numeroTelefonico;
    }

    public String getIndirizzo() {
        if (indirizzo != null){
            return indirizzo;
        }
        return "Indirizzo";
    }

    public String getCitta() {
        if (citta != null) {
            return citta;
        }
        return "città";
    }

    public String getProvincia() {
        if (provincia != null) {
            return provincia;
        }
        return "Provincia";
    }

    public String getPaese() {
        if (paese != null) {
            return paese;
        }
        return "Paese";
    }

    public String getCap() {
        if (cap != null) {
            return cap;
        }
        return  "CAP";
    }

    public String getPrefisso() {
        if(prefisso != null) {
            return prefisso;
        }
        return " ";
    }

    @Override
    public String toString() {
        return  cognome + "\n" +
                nome + "\n" +
                getPrefisso() + " " + numeroTelefonico + "\n" +
                getIndirizzo() + ", " + getCitta() + " (" + getProvincia() + ") " + getCap() + ", " + getPaese() + "\n" +
                "Contatto creato in data: " + dataCreazione.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));
    }
}
