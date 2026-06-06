package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class Comando {
    protected String parametro;
    protected IO ioconsole;

    /**
     * esecuzione del comando
     */
    public void esegui(Partita partita){};

    /*
     * impostazione del parametro (inutile, ma richiesto dalla interface)
     */
    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    /* 
     * impostazione della console
     */
    public void setIOConsole(IO ioconsole) {
        this.ioconsole = ioconsole;
    }
}
