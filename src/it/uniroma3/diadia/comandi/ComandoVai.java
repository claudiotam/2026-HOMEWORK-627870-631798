package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.giocatore.Giocatore;

public class ComandoVai implements Comando {
    private String direzione;
    private IO ioconsole;

    /**
     * esecuzione del comando	
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
     */
    @Override
    public void esegui(Partita partita) {
        Giocatore giocatore = partita.getGiocatore();
        if (giocatore == null) {
            this.ioconsole.mostraMessaggio("Non trovo il giocatore");
            return;
        }
        giocatore.vai(this.direzione);
    }

    /*
     * impostazione del parametro (cioè la direzione)
     */
    @Override
    public void setParametro(String parametro) {
        this.direzione = parametro;
    }

    /* 
     * impostazione della console
     */
    @Override
    public void setIOConsole(IO ioconsole) {
      this.ioconsole = ioconsole;
    }
}