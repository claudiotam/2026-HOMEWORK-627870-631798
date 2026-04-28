package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Giocatore {

    static final private int CFU_INIZIALI = 12;

    private Borsa borsa;
    private int cfu;
    private Stanza stanzaCorrente;
    private IO ioconsole;

    public Giocatore(IO ioconsole) {
        this(ioconsole, CFU_INIZIALI);
    }

    public Giocatore(IO ioconsole, int cfu_iniziali) {
        this.ioconsole = ioconsole;
        this.borsa = new Borsa();
        this.cfu = cfu_iniziali;
    }

    /**
     * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
     * e ne stampa il nome, altrimenti stampa un messaggio di errore
     */
    public void vai(String direzione) {
        if (direzione == null) {
            this.ioconsole.mostraMessaggio("Dove vuoi andare? Specifica una direzione");
            return;
        }
        Stanza prossimaStanza = stanzaCorrente.getStanzaAdiacente(direzione);
        if (prossimaStanza == null) {
            this.ioconsole.mostraMessaggio("Direzione inesistente");
            return;
        }
        stanzaCorrente = prossimaStanza;
        this.ioconsole.mostraMessaggio("Ora ti trovi in: " + stanzaCorrente.getNome());
    }

    /*
     * forwarding dei metodi borsa
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
        return this.borsa.addAttrezzo(attrezzo) ;
    }
    
    public boolean hasAttrezzo(String nomeAttrezzo) {
    	return this.borsa.hasAttrezzo(nomeAttrezzo);
    }
    
    public Attrezzo getAttrezzo(String nomeAttrezzo) {
    	return this.borsa.getAttrezzo(nomeAttrezzo);
    }
    
    public boolean removeAttrezzo(String attrezzo) {
        return this.borsa.removeAttrezzo(attrezzo) ;
    }

    /*
     * metodi gestione cfu
     */
    public void mettiUnCfu() {
    	this.cfu ++;
    }
    
    public boolean hasZeroCfu() {
    	return this.cfu <= 0;
    }

    public int getCfu() {
        return this.cfu;
    }

    public void setCfu(int cfu) {
        this.cfu = cfu;
    }

    public void togliUnCfu() {
        this.cfu--;
    }
    
    /*
     * metodi impostazione stanza eccezionali
     * normalmente il giocatore si imposta la stanza da sè
     */
    public Stanza getStanzaCorrente() {
        return this.stanzaCorrente;
    }

    public void setStanzaCorrente(Stanza stanza) {
        this.stanzaCorrente = stanza;
    }

    public Borsa getBorsa() {
        return this.borsa;
    }

    /*
     * metodi prendi e posa attrezzo
     */
    public void prendi(String nomeAttrezzo) {
        boolean trovato = this.stanzaCorrente.hasAttrezzo(nomeAttrezzo);
        if (!trovato) {
            ioconsole.mostraMessaggio("attrezzo non trovato");
            return;
        }

        Attrezzo attrezzo = this.stanzaCorrente.getAttrezzo(nomeAttrezzo);
        boolean centra = this.borsa.acceptsAttrezzo(attrezzo);
        if (!centra) {
            ioconsole.mostraMessaggio("attrezzo non c'entra nella borsa");
            return;
        }

        this.stanzaCorrente.removeAttrezzo(nomeAttrezzo);
        this.borsa.addAttrezzo(attrezzo);
        ioconsole.mostraMessaggio("attrezzo preso");
    }

    public void posa(String nomeAttrezzo) {
        boolean trovato = this.borsa.hasAttrezzo(nomeAttrezzo);
        if (!trovato) {
            ioconsole.mostraMessaggio("attrezzo non trovato nella borsa");
            return;
        }

        Attrezzo attrezzo = this.borsa.getAttrezzo(nomeAttrezzo);
        boolean centra = this.stanzaCorrente.acceptsAttrezzo(attrezzo);
        if (!centra) {
            ioconsole.mostraMessaggio("attrezzo non c'entra");
            return;
        }
        
        this.borsa.removeAttrezzo(nomeAttrezzo);
        this.stanzaCorrente.addAttrezzo(attrezzo);
        ioconsole.mostraMessaggio("attrezzo posato");
    }

}
