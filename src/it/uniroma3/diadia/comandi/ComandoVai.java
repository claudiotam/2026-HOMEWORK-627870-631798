package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.giocatore.Giocatore;

public class ComandoVai extends Comando {
    /**
     * esecuzione del comando	
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
     */
    @Override
    public void esegui(Partita partita) {
        Giocatore giocatore = partita.getGiocatore();
        if (giocatore == null) {
            ioconsole.mostraMessaggio("Non trovo il giocatore");
            return;
        }

        Direzione direzione = getDirezione();
        if (direzione == null) {
            ioconsole.mostraMessaggio("Dove vuoi andare? Specifica una direzione");
            return;
        }
        
        giocatore.vai(direzione);
    }

    Direzione getDirezione() {
        for (Direzione d : Direzione.values()) {
            if (d.name().equalsIgnoreCase(this.parametro))
                return d;
        }
        return null;
    }
}