package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends Comando {
    /**
     * esecuzione del comando
     */
    @Override
    public void esegui(Partita partita) {
        ioconsole.mostraMessaggio(partita.getGiocatore().getStanzaCorrente().getDescrizione());
    }
}
