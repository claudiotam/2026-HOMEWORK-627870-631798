/**
 * Questa classe modella una partita del gioco
 *
 * @author Claudio Tam
 * @see Stanza
 * @version base
 */

package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;
import it.uniroma3.diadia.giocatore.Giocatore;

public class Partita {

    static final private String MESSAGGIO_BENVENUTO = ""+
        "Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
        "Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
        "I locali sono popolati da strani personaggi, " +
        "alcuni amici, altri... chissa!\n"+
        "Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
        "puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
        "o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
        "Per conoscere le istruzioni usa il comando 'aiuto'.";
    

    private boolean finita;
    private Giocatore giocatore;
    private Labirinto labirinto;
    private IO ioconsole;
    
    public Partita(IO ioconsole) {
        this.ioconsole = ioconsole;
        this.labirinto = new Labirinto();
        labirinto.creaLabirintoBase();
        this. giocatore = new Giocatore(ioconsole);
        giocatore.setStanzaCorrente(labirinto.getStanzaIniziale());
        this.finita = false;
    }

    public void gioca() {
        /*
         *  loop principale while (leggi istruzione) do processa istruzione
         */
        String istruzione;

        ioconsole.mostraMessaggio(MESSAGGIO_BENVENUTO);
        
        do {
            istruzione = ioconsole.leggiRiga();
        } while (!processaIstruzione(istruzione));
    }   

    /**
     * Processa una istruzione 
     *
     * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
     */
    private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire;
        FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica(this.ioconsole);
		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this);
		
		giocatore.togliUnCfu();
		
		if (this.isFinita()) { 
			if (this.vinta()) {
				this.ioconsole.mostraMessaggio("Hai vinto!");
			}
			if (giocatore.hasZeroCfu()) {
				this.ioconsole.mostraMessaggio("Hai perso! Non hai manco un CFU!");
			}
			this.fine();
			return true;
		}
		return false;
    }

    // implementazioni dei comandi dell'utente:

    /**
     * Comando "Fine".
     */
    private void fine() {
        ioconsole.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
    }

    /**
     * Restituisce vero se e solo se la partita e' stata vinta
     * @return vero se partita vinta
     */
    public boolean vinta() {
        return this.giocatore.getStanzaCorrente()== this.labirinto.getStanzaVincente();
    }

    /**
     * Restituisce vero se e solo se la partita e' finita
     * @return vero se partita finita
     */
    public boolean isFinita() {
        return finita || vinta() || this.giocatore.hasZeroCfu();
    }

    /**
     * Imposta la partita come finita
     *
     */
    public void setFinita() {
        this.finita = true;
    }

    public Giocatore getGiocatore() {
        return this.giocatore;
    }

    public Labirinto getLabirinto() {
        return this.labirinto;
    }

}
