package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoBorsa extends Comando {
    /**
     * esecuzione del comando
     */
    @Override
    public void esegui(Partita partita) {
        ioconsole.mostraMessaggio(partita.getGiocatore().getBorsa().getDescrizione());
    }
}
