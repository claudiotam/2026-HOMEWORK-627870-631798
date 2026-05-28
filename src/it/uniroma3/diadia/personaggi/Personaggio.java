package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public abstract class Personaggio {
    private   String  nome;
    private   String  presentazione;
    private   boolean haSalutato;
    protected IO      ioconsole;

    public Personaggio(IO ioconsole, String nome, String presentazione) {
        this.ioconsole = ioconsole;
        this.nome = nome;
        this.presentazione = presentazione;
        this.haSalutato = false;
    }

    public String getNome() {
        return this.nome;
    }

    public void saluta() {
        if (!haSalutato) {
            this.haSalutato = true;
            ioconsole.mostraMessaggio("Ciao, il mio nome è " + this.getNome() + ". " + this.presentazione);
        }
        else {
            ioconsole.mostraMessaggio("Ci siamo già salutati una volta. Non ti saluterò più. ");
        }
    }

    abstract public void riceviRegalo(Partita partita, Attrezzo attrezzo);

    @Override
    public String toString() {
        return "<oggetto Personaggio " + this.nome + ">";
    }
}