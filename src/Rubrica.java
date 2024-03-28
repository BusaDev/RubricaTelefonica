import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Rubrica implements Serializable {
    static Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    List<Contatto> rubrica = new ArrayList<>();
    List<Local> savedLocal = new ArrayList<>();
    public void aggiungiContatto(Contatto c) {
        rubrica.add(c);
    }
    public void aggiungiLocal(Local l) {
        savedLocal.add(l);
    }
    public Boolean visualizzaContatto(){
        if(!rubrica.isEmpty()) {
            for (int i = 0; i < rubrica.size(); i++) {
                System.out.println(
                        "[" + i + "] " +
                        rubrica.get(i).getCognome() + " " +
                        rubrica.get(i).getNome() + " " +
                        rubrica.get(i).getNumeroTelefonico()
                        );
            }
            return true;
        }else{
            System.out.println("La rubrica è vuota!");
            return false;
        }
    }
    public void ordinaRubrica(){
            rubrica.sort(Comparator.comparing(contatto -> contatto.getCognome()));
    }
    public void cancellaRubrica(){
        rubrica.clear();
    }
    public void visualizzaContattoEsteso(int scelta){
        if(scelta < rubrica.size()) {
            System.out.println(rubrica.get(scelta));
        }else{
            System.out.println("Nessun contatto con nella posizione digitata");
        }
    }

    /**
     *---Metodi di modifica parametri---
     * tutti i metodi "modificaContatto*" modificano il paramentro con input inserito,
     * se l'input è un campo vuoto il parametro rimarrà invariato.
     */
    public void modificaContattoNome(int i,String input){
        if(!input.isEmpty()) {
            rubrica.get(i).setNome(input);
        }else{
            System.out.println("Nome rimasto invariato!");
        }
    }
    public void modificaContattoCognome(int i,String input){
        if(!input.isEmpty()) {
            rubrica.get(i).setCognome(input);
        }else{
            System.out.println("Cognome rimasto invariato!");
        }
    }
    public void modificaContattoNumero(int i,String input){
        if(!input.isEmpty()) {
            rubrica.get(i).setNumeroTelefonico(input);
        }else{
            System.out.println("Numero Telefonico rimasto invariato!");
        }
    }
    public void modificaContattoIndirizzo(int i,String input){
        if(!input.isEmpty()) {
            rubrica.get(i).setIndirizzo(input);
        }else{
            System.out.println("Indirizzo rimasto invariato!");
        }
    }

    /**
     *---Metodo di modifica città---
     * in aggiunta questo metodo richiama il metodo di check località
     */
    public Boolean modificaContattoCitta(int i,String input){
        if(!input.isEmpty()) {
            rubrica.get(i).setCitta(input);
            Local tempLocal = checkLocal(input);
            if(tempLocal.isSaved()) {
                rubrica.get(i).setProvincia(tempLocal.getProvincia());
                rubrica.get(i).setPaese(tempLocal.getPaese());
                rubrica.get(i).setPrefisso(tempLocal.getPrefisso());
                return true;
            }
            return false;
        }else{
            System.out.println("Città rimasto invariato!");
            return false;
        }
    }

    /**
     *---Metodo per il check dell'array delle località---
     * Se non trova nessuna località con la città in entrata il metodo tornerà un istanza Local con variabile isSaved a false
     * Se trova la città all'interno dell' array verrà chiesto se si vuole eseguire "l'autoriempimento" degli altri campi;
     * -Se si accetta l'autoriempimento verranno il metodo tornerà la località trovata;
     * -Se non si accetta l'autoriempimento verrà richiesto di reinserire i campi sovrascrivendoli e tornando la località sovrascritta;
     */
    public Local checkLocal(String citta){

        Local nullLocal = new Local(false);
        for (Local local : savedLocal){
            if(local.getCitta().equalsIgnoreCase(citta)){
                System.out.println("Autoriempimento: " +
                                local.getCitta() +
                                local.getProvincia() +
                                local.getPaese() +
                                " prefisso telefonico: " +
                                local.getPrefisso()
                        );

                System.out.println("è corretto?");
                while(true) {
                    System.out.println("y/n");
                    String scelta = scanner.next();
                    if(scelta.equalsIgnoreCase("y")){
                        return local;
                    }else if (scelta.equalsIgnoreCase("n")){
                        System.out.print("Provincia: ");
                        String provincia = inserimentoProvincia();
                        local.setProvincia(provincia);
                        System.out.print("Paese: ");
                        String paese = scanner.next();
                        local.setPaese(paese);
                        System.out.print("Prefisso: ");
                        String prefisso = scanner.next();
                        local.setPrefisso(prefisso);
                        return local;
                    }else{
                        System.out.println("Input errato");
                    }

                }
            }
        }

        System.out.println("Nessuna Località trovata con questa città");

        return nullLocal;
    }
    public void modificaContattoProvincia(int i,String input){
        if(!input.isEmpty()) {
            rubrica.get(i).setProvincia(input);
        }else{
            System.out.println("Provincia rimasto invariato!");
        }
    }
    public void modificaContattoCap(int i,String input){
        if(!input.isEmpty()) {
            rubrica.get(i).setCap(input);
        }else{
            System.out.println("CAP rimasto invariato!");
        }
    }
    public void modificaContattoPaese(int i,String input){
        if(!input.isEmpty()) {
            rubrica.get(i).setPaese(input);
        }else{
            System.out.println("Paese rimasto invariato!");
        }
    }
    public void modificaContattoPrefisso(int i,String input){
        if(!input.isEmpty()) {
            rubrica.get(i).setPrefisso(input);
        }else{
            System.out.println("Prefisso rimasto invariato!");
        }
    }
    /**
     *---Metodo per il check dell'input della provincia---
     * se l'input contiene numeri o ha una lunghezza diversa da 2 verrà richiesto di inserirne un'altra
     */
    public String inserimentoProvincia(){
        while(true){
            String provincia = scanner.next();
            if (provincia.length() != 2 || provincia.matches("[0-9]+")){
                System.out.println("inserisci una provincia corretta, es. 'CT'");
            }else{
                return provincia.toUpperCase();
            }
        }
    }
}

