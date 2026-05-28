package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoRegala implements Comando {
    private String nome_attrezzo;
    private IO ioconsole;

    /**
     * esecuzione del comando
     */
    @Override
    public void esegui(Partita partita) {
        if (nome_attrezzo == null) {
            this.ioconsole.mostraMessaggio("Quale attrezzo vuoi regalare? Specifica un nome di attrezzo");
            return;
        }
        partita.getGiocatore().regala(partita, nome_attrezzo);
    }

    /*
     * impostazione del parametro (cioè il nome_attrezzo)
     */
    @Override
    public void setParametro(String nome_attrezzo) {
        this.nome_attrezzo = nome_attrezzo;
    }

    /*
     * impostazione della console
     */
    @Override
    public void setIOConsole(IO ioconsole) {
        this.ioconsole = ioconsole;
    }
}
