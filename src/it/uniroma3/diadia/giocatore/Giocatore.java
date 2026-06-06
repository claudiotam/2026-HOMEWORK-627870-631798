package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Personaggio;

public class Giocatore {

    private static final int CFU_INIZIALI = 12;

    private Borsa borsa;
    private int cfu;
    private Stanza stanzaCorrente;
    private IO ioconsole;

    public Giocatore(IO ioconsole, Stanza stanzaIniziale) {
        this(ioconsole, stanzaIniziale, CFU_INIZIALI);
    }

    public Giocatore(IO ioconsole, Stanza stanzaIniziale, int cfu_iniziali) {
        this.ioconsole = ioconsole;
        this.borsa = new Borsa();
        this.cfu = cfu_iniziali;
        this.stanzaCorrente = stanzaIniziale;
    }

    /**
     * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
     * e ne stampa il nome, altrimenti stampa un messaggio di errore
     */
    public void vai(Direzione direzione) {
        if (direzione == null) {
            ioconsole.mostraMessaggio("Dove vuoi andare? Specifica una direzione");
            return;
        }

        Stanza prossimaStanza = stanzaCorrente.getStanzaAdiacente(direzione);
        if (prossimaStanza == null) {
            ioconsole.mostraMessaggio("Direzione inesistente");
            return;
        }
        
        stanzaCorrente = prossimaStanza;
        ioconsole.mostraMessaggio("Ora ti trovi in: " + stanzaCorrente.getNome());
    }

    /*
     * forwarding dei metodi borsa
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
        return borsa.addAttrezzo(attrezzo) ;
    }
    
    public boolean hasAttrezzo(String nomeAttrezzo) {
    	return borsa.hasAttrezzo(nomeAttrezzo);
    }
    
    public Attrezzo getAttrezzo(String nomeAttrezzo) {
    	return borsa.getAttrezzo(nomeAttrezzo);
    }
    
    public void removeAttrezzo(String attrezzo) {
        borsa.removeAttrezzo(attrezzo) ;
    }

    /*
     * metodi gestione cfu
     */
    public void mettiUnCfu() {
    	cfu ++;
    }
    
    public boolean hasZeroCfu() {
    	return cfu <= 0;
    }

    public int getCfu() {
        return cfu;
    }

    public void setCfu(int cfu) {
        this.cfu = cfu;
    }

    public void togliUnCfu() {
        cfu--;
    }
    
    /*
     * metodi impostazione stanza eccezionali
     * normalmente il giocatore si imposta la stanza da sè
     */
    public Stanza getStanzaCorrente() {
        return stanzaCorrente;
    }

    public void setStanzaCorrente(Stanza stanza) {
        stanzaCorrente = stanza;
    }

    public Borsa getBorsa() {
        return borsa;
    }

    /*
     * metodi prendi e posa attrezzo
     */
    public void prendi(String nomeAttrezzo) {
        boolean trovato = stanzaCorrente.hasAttrezzo(nomeAttrezzo);
        if (!trovato) {
            ioconsole.mostraMessaggio("attrezzo non trovato");
            return;
        }

        Attrezzo attrezzo = stanzaCorrente.getAttrezzo(nomeAttrezzo);
        boolean centra = borsa.acceptsAttrezzo(attrezzo);
        if (!centra) {
            ioconsole.mostraMessaggio("attrezzo non c'entra nella borsa");
            return;
        }

        stanzaCorrente.removeAttrezzo(nomeAttrezzo);
        borsa.addAttrezzo(attrezzo);
        ioconsole.mostraMessaggio("attrezzo preso");
    }

    public void posa(String nomeAttrezzo) {
        boolean trovato = borsa.hasAttrezzo(nomeAttrezzo);
        if (!trovato) {
            ioconsole.mostraMessaggio("attrezzo da posare non trovato nella borsa");
            return;
        }

        Attrezzo attrezzo = borsa.getAttrezzo(nomeAttrezzo);
        boolean centra = stanzaCorrente.acceptsAttrezzo(attrezzo);
        if (!centra) {
            ioconsole.mostraMessaggio("attrezzo non c'entra");
            return;
        }
        
        borsa.removeAttrezzo(nomeAttrezzo);
        stanzaCorrente.addAttrezzo(attrezzo);
        ioconsole.mostraMessaggio("attrezzo posato");
    }

    public void regala(Partita partita, String nomeAttrezzo) {
        boolean trovato = borsa.hasAttrezzo(nomeAttrezzo);
        if (!trovato) {
            ioconsole.mostraMessaggio("attrezzo da regalare non trovato nella borsa");
            return;
        }

        Attrezzo attrezzo = borsa.getAttrezzo(nomeAttrezzo);

        if (!stanzaCorrente.hasPersonaggio()) {
            ioconsole.mostraMessaggio("in questa stanza non c'è alcun personaggio a cui regalare");
            return;
        }

        Personaggio personaggio = stanzaCorrente.getPersonaggio();
        borsa.removeAttrezzo(nomeAttrezzo);
        ioconsole.mostraMessaggio("attrezzo regalato");
        personaggio.riceviRegalo(partita, attrezzo);
    }

}
