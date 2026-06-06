/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author Claudio Tam
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.giocatore.Giocatore;
import it.uniroma3.diadia.personaggi.Cane;

public class DiaDia {

    public static void main(String[] argc) {
        //crea una console (il comando posa ha bisogno di una console per mostrare gli errori)
        IO ioconsole = new IOConsole();

        //crea un labirinto

        // Setup per ogni test, viene eseguito prima di ciascun test
        // Crea un nuovo labirinto
        Labirinto.LabirintoBuilder builder = new Labirinto.LabirintoBuilder();
        Labirinto labirinto = builder.creaLabirintoBase().getLabirinto();

        //crea dei personaggi
        labirinto.getStanzaIniziale().setPersonaggio(new Cane(ioconsole));

        //crea un giocatore
        Giocatore giocatore;

        //Tenta il caricamento delle properties
        Configuratore conf = new Configuratore();
        if (conf.getCFU()!=null) {
            giocatore = new Giocatore(ioconsole, labirinto.getStanzaIniziale(), conf.getCFU());
            ioconsole.mostraMessaggio("<<file properties caricato correttamente, CFU letti>>");
        }
        else {
            giocatore = new Giocatore(ioconsole, labirinto.getStanzaIniziale());
        }

        //crea partita con inclusi giocatore, borsa (il comando posa lavora su una partita)
        Partita partita = new Partita(ioconsole, giocatore);


        partita.gioca();
    }
}