package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.giocatore.Giocatore;

public class ComandoVai implements Comando {
    private String nomeDirezione;
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

        Direzione direzione = getDirezione();
        if (direzione == null) {
            this.ioconsole.mostraMessaggio("Dove vuoi andare? Specifica una direzione");
            return;
        }
        
        giocatore.vai(direzione);
    }

    Direzione getDirezione() {
        for (Direzione d : Direzione.values()) {
            if (d.name().equalsIgnoreCase(nomeDirezione))
                return d;
        }
        return null;
    }

    /*
     * impostazione del parametro (cioè la direzione)
     */
    @Override
    public void setParametro(String parametro) {
        this.nomeDirezione = parametro;
    }

    /* 
     * impostazione della console
     */
    @Override
    public void setIOConsole(IO ioconsole) {
      this.ioconsole = ioconsole;
    }
}