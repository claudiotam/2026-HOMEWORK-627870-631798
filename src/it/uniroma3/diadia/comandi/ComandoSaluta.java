package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoSaluta implements Comando {
    private IO ioconsole;

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

    /*
     * impostazione del parametro
     */
    @Override
    public void setParametro(String parametro) {}

    /*
     * impostazione della console
     */
    @Override
    public void setIOConsole(IO ioconsole) {
        this.ioconsole = ioconsole;
    }
}
