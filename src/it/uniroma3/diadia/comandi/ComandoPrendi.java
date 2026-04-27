package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.giocatore.Giocatore;

public class ComandoPrendi implements Comando {
    private String    nome_attrezzo;
    private IO ioconsole;

    /**
     * esecuzione del comando
     */
    @Override
    public void esegui(Partita partita) {
    if (nome_attrezzo == null) {
        this.ioconsole.mostraMessaggio("Quale attrezzo vuoi prendere? Specifica un nome di attrezzo");
        return;
    }
    Giocatore giocatore = partita.getGiocatore();
    if (giocatore == null) {
        this.ioconsole.mostraMessaggio("Non trovo il giocatore");
        return;
    }
    giocatore.prendi(nome_attrezzo);
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
