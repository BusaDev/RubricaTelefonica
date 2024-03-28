import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    Rubrica rubrica;

    public Menu(Rubrica rubrica) {
        this.rubrica = rubrica;
    }

    public void menuStart() throws IOException {
        System.out.println("Rubrica");
        while(true){
            System.out.println(
                            "Digita 1 per visualizzare la rubrica\n"+
                            "Digita 2 per aggiungere un contatto alla rubrica\n"+
                            "Digita 3 per rimuovere un contatto dalla rubrica\n"+
                            "Digita 4 per cercare un contatto nella rubrica\n"+
                            "Digita 5 per cancellare la rubrica\n"+
                            "Digita 0 per uscire dalla rubrica"
                    );
            String scelta = scanner.next();
            switch (scelta){
                case "1":
                    if(rubrica.visualizzaContatto()) {
                        menuVisContatti();
                    }
                    break;
                case "2":
                    rubrica.aggiungiContatto(creaNuovoContatto());
                    rubrica.ordinaRubrica();
                    salvaRubrica(rubrica);
                    System.out.println("nuovo contatto inserito!");
                    break;
                case "3":
                    System.out.println("metodo ancora non implementato");
                    break;
                case "4":
                    System.out.println("metodo ancora non implementato");
                    break;
                case "5":
                    rubrica.cancellaRubrica();
                    System.out.println("Rubrica cancellata con successo");
                    break;
                case "0":
                    System.out.println("Arrivederci!");
                    return;
                default:
                    System.out.println("Digita un comando corretto!");
                    break;
            }
        }
    }

    /**
     * -------Salvataggio/Sovrascrizione---------
     * Metodo per salvare/sovrascrivere l'oggetto Rubrica "rubrica" all'interno del file "rubrica.ser"
     * @param rubrica
     */
    public void salvaRubrica(Rubrica rubrica) throws IOException {
        FileOutputStream outputStream = new FileOutputStream("src/Files/rubrica.ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(rubrica);
        objectOutputStream.close();
    }

    /**
     *---Metodo per creare un nuovo contatto nella rubrica---
     * è possibile creare un contatto "corto" inserendo solo "nome", "cognome" e "numero telefonico",
     * nel caso si voglia creare un contatto completo all'inserimento della città verra richiamato il metodo di check dell'array delle località salvate;
     * Se il metodo di check tornerà una località con "isSaved" false verrà richiesto di inserire i campi creando oltre al contatto anche la nuova località;
     * Se il metodo di check tornerà una località con "isSaved" true verrà utilizzata per riempire i campi rimanenti.
     */
    public Contatto creaNuovoContatto(){
        System.out.print("Nome: ");
        String nome = scanner.next();
        System.out.print("Cognome: ");
        String cognome = scanner.next();
        System.out.print("NumeroTelefonico: ");
        String numeroTelefonico = scanner.next();
        System.out.println("Aggiungere ulteriori informazioni al contatto? Potranno essere aggiunte in futuro.");
        while(true) {
            System.out.println("y/n");
            String scelta = scanner.next();
            if(scelta.equalsIgnoreCase("n")) {
                Contatto contatto = new Contatto(nome, cognome, numeroTelefonico);
                return contatto;
            } else if (scelta.equalsIgnoreCase("y")) {
                System.out.print("Indirizzo: ");
                String indirizzo = scanner.next();
                System.out.print("CAP: ");
                String cap = scanner.next();
                System.out.print("Città: ");
                String citta = scanner.next();
                Local tempLocal = rubrica.checkLocal(citta);
                if(tempLocal.isSaved()){
                    Contatto contatto = new Contatto(nome, cognome, numeroTelefonico,indirizzo,citta,tempLocal.getProvincia(),tempLocal.getPaese(), cap, tempLocal.getPrefisso());
                    return contatto;
                }else{
                    System.out.print("Provincia: ");
                    String provincia = inserimentoProvincia();
                    System.out.print("Paese: ");
                    String paese = scanner.next();
                    System.out.print("Prefisso: ");
                    String prefisso = scanner.next();
                    Local newLocal = new Local(citta,provincia,paese,cap,prefisso);
                    rubrica.aggiungiLocal(newLocal);
                    Contatto contatto = new Contatto(nome, cognome, numeroTelefonico,indirizzo,citta,provincia,paese,cap,prefisso);
                    return contatto;
                }
            }else{
                System.out.println("Digita un comando valido!");
            }
        }
    }

    /**
     *---Menu per la gestione dei contatti---
     * Dopo aver visualizzato la rubrica si può selezionare un contatto inserendo la posizione che compare dietro o premere "e" per tornare indietro,
     *  - scelta.matches("[a-zA-Z]+") - questo if è necessario per far ripetere la scelta in caso di input diverso da un numero e diverso da "e"
     */
    public void menuVisContatti() throws IOException {
        while(true){
            System.out.println("Digita posizione del contatto che si vuole visualizzare o 'e' per tornare indietro");
            String scelta = scanner.next();
            if (scelta.equalsIgnoreCase("e")){
                return;
            }else if (scelta.matches("[a-zA-Z]+")){
                System.out.println("Digita una posizione corretta!");
            }else{
                rubrica.visualizzaContattoEsteso(Integer.parseInt(scelta));
                modificaContatto(Integer.parseInt(scelta));
                return;
            }
        }
    }

    /**
     *---Metodo per modificare il contatto selezionato---
     * all'inserimento della città verra richiamato il metodo di check dell'array delle località salvate:
     * Se il metodo di check tornerà una località con "isSaved" false verrà richiesto di inserire i campi modificando il contatto e creando la nuova località;
     * Se il metodo di check tornerà una località con "isSaved" true verrà utilizzata per riempire i campi rimanenti.
     */
    public void modificaContatto(int i) throws IOException {
        while(true){
            System.out.println("Vuoi modificare il contatto corrente?");
            System.out.println("y/n");
            String scelta = scanner.next();
            if(scelta.equalsIgnoreCase("n")){
                return;
            }else if (scelta.equalsIgnoreCase("y")){
                System.out.print("Nome: ");
                String nome = scanner.next();
                rubrica.modificaContattoNome(i,nome);
                System.out.print("Cognome: ");
                String cognome = scanner.next();
                rubrica.modificaContattoCognome(i,cognome);
                System.out.print("Numero Telefonico: ");
                String numero = scanner.next();
                rubrica.modificaContattoNumero(i,numero);
                System.out.print("Indirizzo: ");
                String indirizzo = scanner.next();
                rubrica.modificaContattoIndirizzo(i,indirizzo);
                System.out.print("CAP: ");
                String cap = scanner.next();
                rubrica.modificaContattoCap(i,cap);
                System.out.print("Città: ");
                String citta = scanner.next();
                if(!rubrica.modificaContattoCitta(i,citta)) {
                    System.out.print("Provincia: ");
                    String provincia = inserimentoProvincia();
                    rubrica.modificaContattoProvincia(i,provincia);
                    System.out.print("Paese: ");
                    String paese = scanner.next();
                    rubrica.modificaContattoPaese(i,paese);
                    System.out.print("Prefisso: ");
                    String prefisso = scanner.next();
                    rubrica.modificaContattoPrefisso(i,prefisso);
                    Local newLocal = new Local(citta,provincia,paese,cap,prefisso);
                    rubrica.aggiungiLocal(newLocal);
                    rubrica.ordinaRubrica();
                    salvaRubrica(rubrica);
                    return;
                }
                rubrica.ordinaRubrica();
                salvaRubrica(rubrica);
                return;
            }else{
                System.out.println("Input Errato");
            }

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
