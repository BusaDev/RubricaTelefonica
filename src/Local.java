import java.io.Serializable;

public class Local implements Serializable {
    private String citta;
    private String provincia;
    private String paese;
    private String prefisso;
    private boolean saved;

    public Local(boolean saved) {
        this.saved = saved;
    }

    public Local(String citta, String provincia, String paese, String cap, String prefisso) {
        this.citta = citta;
        this.provincia = provincia;
        this.paese = paese;
        this.prefisso = prefisso;
        this.saved = true;
    }

    public boolean isSaved() {
        return saved;
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


    public void setPrefisso(String prefisso) {
        this.prefisso = prefisso;
    }

    public String getCitta() {
        return citta;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getPaese() {
        return paese;
    }


    public String getPrefisso() {
        return prefisso;
    }
}
