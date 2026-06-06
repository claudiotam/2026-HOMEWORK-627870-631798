package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends Comando {
    /**
     * esecuzione del comando
     */
    @Override
    public void esegui(Partita partita) {
        ioconsole.mostraMessaggio("Comandi disponibili aiuto borsa fine guarda posa prendi regala saluta vai.");
    }

}
