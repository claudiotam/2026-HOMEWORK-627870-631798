package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoSaluta extends Comando {
    /**
     * esecuzione del comando
     */
    @Override
    public void esegui(Partita partita) {
        Stanza stanzaCorrente = partita.getGiocatore().getStanzaCorrente();
        if (!stanzaCorrente.hasPersonaggio()) {
            ioconsole.mostraMessaggio("nessun personaggio da salutare qui");
            return;
        }
        stanzaCorrente.getPersonaggio().saluta();
    }
}
