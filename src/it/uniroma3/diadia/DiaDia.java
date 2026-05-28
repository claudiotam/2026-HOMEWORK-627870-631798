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
import it.uniroma3.diadia.personaggi.Cane;

public class DiaDia {

    public static void main(String[] argc) {
        IO ioconsole = new IOConsole();
        Labirinto labirinto = new Labirinto();
        labirinto.creaLabirintoBase();
        labirinto.getStanzaIniziale().setPersonaggio(new Cane(ioconsole));

        Partita partita = new Partita(ioconsole, labirinto);
        partita.gioca();
    }
}