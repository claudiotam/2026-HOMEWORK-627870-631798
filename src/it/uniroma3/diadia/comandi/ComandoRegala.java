package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoRegala extends Comando {
    /**
     * esecuzione del comando
     */
    @Override
    public void esegui(Partita partita) {
        if (this.parametro == null) {
            ioconsole.mostraMessaggio("Quale attrezzo vuoi regalare? Specifica un nome di attrezzo");
            return;
        }
        partita.getGiocatore().regala(partita, this.parametro);
    }
}
