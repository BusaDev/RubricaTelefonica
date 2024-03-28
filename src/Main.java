import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        /**
         * -------Fase di Caricamento-------
         * Carica il file "rubrica.ser" dentro l'istanza rubrica,
         * se non esiste alcun file crea una nuova istanza di Rubrica.
         */
        Rubrica rubrica = new Rubrica();
        try {
            FileInputStream fileInputStream = new FileInputStream("src/Files/rubrica.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            rubrica = (Rubrica) objectInputStream.readObject();
        }catch(FileNotFoundException e){

        }catch (InvalidClassException e){
            System.out.println("Errore File Rubrica corrotto, il file verrà ripristinato");
            File file = new File("src/Files/rubrica.ser");
            file.delete();
        }
        Menu menu  = new Menu(rubrica);
        menu.menuStart();


    }
}