package it.uniroma3.diadia.comandi;

import java.util.Scanner;

import it.uniroma3.diadia.IO;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {
    IO ioconsole;

    public FabbricaDiComandiRiflessiva(IO ioconsole) {
        this.ioconsole = ioconsole;
    }
    
    /*
    * la funzione chiamante legge una intera riga usando uno scanner e chiama la funzione costruisciComando
    * la funzione costruisciComando estrae della riga la prima 
    * parola (nomeComando) e la possibile seconda parola (parametro) usando un altro scanner
    * la funzione costruisciComando cerca una classe corrispondente 
    * alla stringa, usando il metodo a fisarmonica o a riflessione
     */
    @Override
    public Comando costruisciComando(String rigaIstruzione) {

        Scanner scannerDiParole = new Scanner(rigaIstruzione); // es. ‘vai sud’
        String nomeComando = null; // es. ‘vai’
        String parametro = null; // es. ‘sud’
        Comando comando = null;
        
        if (scannerDiParole.hasNext()) 
            nomeComando = scannerDiParole.next();// prima parola: nome del comando
        if (scannerDiParole.hasNext())
            parametro = scannerDiParole.next();// seconda parola: eventuale parametro
        scannerDiParole.close();

        if (nomeComando==null) {
            nomeComando="nonValido";
        }

        StringBuilder nomeClasse = new StringBuilder("it.uniroma3.diadia.comandi.Comando");
        nomeClasse.append(Character.toUpperCase(nomeComando.charAt(0)));
        // es. nomeClasse: ‘it.uniroma3.diadia.comandi.ComandoV’

        nomeClasse.append(nomeComando.substring(1));
        // es. nomeClasse: ‘it.uniroma3.diadia.comandi.ComandoVai’
        try {
            //cerca la classe specifica col nome corrispondente alla stringa inserita
            Class<?> clazz = Class.forName(nomeClasse.toString());

            //crea un oggetto generico
            comando = (Comando) clazz.getDeclaredConstructor().newInstance();
        }
        catch (ClassNotFoundException e) {
            //se il controllo riflessivo runtime di esistenza classe fallisce,
            // usa il controllo statico con la classe ComandoNonValido
            comando = new ComandoNonValido();
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Guasto riflessivo nella creazione del comando: " + nomeClasse, e);
        }

        comando.setParametro(parametro);
        comando.setIOConsole(ioconsole);

        return comando;
    }
}