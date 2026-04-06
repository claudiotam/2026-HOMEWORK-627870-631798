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

public class DiaDia {

    public static void main(String[] argc) {
        IOConsole ioconsole = new IOConsole();
        Partita partita = new Partita(ioconsole);
        partita.gioca();
    }
}