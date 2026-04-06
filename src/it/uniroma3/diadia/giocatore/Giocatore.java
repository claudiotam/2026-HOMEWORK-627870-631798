package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Giocatore {

    static final private int CFU_INIZIALI = 4;

    private Borsa borsa;
    private int cfu;
    private Stanza stanzaCorrente;
    private IOConsole ioconsole;

    public Giocatore(IOConsole ioconsole) {
        this(ioconsole, CFU_INIZIALI);
    }

    public Giocatore(IOConsole ioconsole, int cfu_iniziali) {
        this.ioconsole = ioconsole;
        this.borsa = new Borsa();
        this.cfu = cfu_iniziali;
    }

    /**
     * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
     * e ne stampa il nome, altrimenti stampa un messaggio di errore
     */
    public void vai(String direzione) {
        if(direzione==null)
            ioconsole.mostraMessaggio("Dove vuoi andare ?");
        Stanza prossimaStanza = null;
        prossimaStanza = this.stanzaCorrente.getStanzaAdiacente(direzione);
        if (prossimaStanza == null)
            ioconsole.mostraMessaggio("Direzione inesistente");
        else {
            stanzaCorrente = prossimaStanza;
            this.togliUnCfu();
        }
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

    public String borsaString() {
        return this.borsa.toString();
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
            ioconsole.mostraMessaggio("attrezzo non c'entra");
            return;
        }

        this.stanzaCorrente.removeAttrezzo(nomeAttrezzo);
        this.borsa.addAttrezzo(attrezzo);
        ioconsole.mostraMessaggio("attrezzo preso");
    }

    public void posa(String nomeAttrezzo) {
        boolean trovato = this.borsa.hasAttrezzo(nomeAttrezzo);
        if (!trovato) {
            ioconsole.mostraMessaggio("attrezzo non trovato");
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
