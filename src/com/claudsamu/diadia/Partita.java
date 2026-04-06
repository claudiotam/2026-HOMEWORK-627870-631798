/**
 * Questa classe modella una partita del gioco
 *
 * @author Claudio Tam
 * @see Stanza
 * @version base
 */

package com.claudsamu.diadia;

<<<<<<< HEAD
public class Partita {

    static final private int CFU_INIZIALI = 20;

    private Stanza stanzaCorrente;
    private Stanza stanzaVincente;
    private boolean finita;
    private int cfu;
    
    public Partita(){
        creaStanze();
        this.finita = false;
        this.cfu = CFU_INIZIALI;
    }

    /**
     * Crea tutte le stanze e le porte di collegamento
     */
    private void creaStanze() {

        /* crea gli attrezzi */
        Attrezzo lanterna = new Attrezzo("lanterna",3);
        Attrezzo osso = new Attrezzo("osso",1);
        
        /* crea stanze del labirinto */
        Stanza atrio = new Stanza("Atrio");
        Stanza aulaN11 = new Stanza("Aula N11");
        Stanza aulaN10 = new Stanza("Aula N10");
        Stanza laboratorio = new Stanza("Laboratorio Campus");
        Stanza biblioteca = new Stanza("Biblioteca");
        
        /* collega le stanze */
        atrio.impostaStanzaAdiacente("nord", biblioteca);
        atrio.impostaStanzaAdiacente("est", aulaN11);
        atrio.impostaStanzaAdiacente("sud", aulaN10);
        atrio.impostaStanzaAdiacente("ovest", laboratorio);
        aulaN11.impostaStanzaAdiacente("est", laboratorio);
        aulaN11.impostaStanzaAdiacente("ovest", atrio);
        aulaN10.impostaStanzaAdiacente("nord", atrio);
        aulaN10.impostaStanzaAdiacente("est", aulaN11);
        aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
        laboratorio.impostaStanzaAdiacente("est", atrio);
        laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
        biblioteca.impostaStanzaAdiacente("sud", atrio);

        /* pone gli attrezzi nelle stanze */
        aulaN10.addAttrezzo(lanterna);
        atrio.addAttrezzo(osso);

        // il gioco comincia nell'atrio
        stanzaCorrente = atrio;  
        stanzaVincente = biblioteca;
    }

    public Stanza getStanzaVincente() {
        return stanzaVincente;
    }

    public void setStanzaCorrente(Stanza stanzaCorrente) {
        this.stanzaCorrente = stanzaCorrente;
    }

    public Stanza getStanzaCorrente() {
        return this.stanzaCorrente;
    }
    
=======
import java.util.Scanner;

public class Partita {


    static final private String MESSAGGIO_BENVENUTO = ""+
        "Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
        "Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
        "I locali sono popolati da strani personaggi, " +
        "alcuni amici, altri... chissa!\n"+
        "Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
        "puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
        "o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
        "Per conoscere le istruzioni usa il comando 'aiuto'.";
    
    static final private String[] elencoComandi = {"vai", "aiuto", "guarda", "fine"};


    private boolean finita;
    private Giocatore giocatore;
    private Labirinto labirinto;
    
    public Partita() {
        this.labirinto = new Labirinto();
        labirinto.creaLabirintoBase();
        this. giocatore = new Giocatore();
        giocatore.setStanzaCorrente(labirinto.getStanzaIniziale());
        this.finita = false;
    }

    public void gioca() {
        /*
         *  loop principale while (leggi istruzione) do processa istruzione
         */
        String istruzione;
        Scanner scannerDiLinee;

        System.out.println(MESSAGGIO_BENVENUTO);
        scannerDiLinee = new Scanner(System.in);
        do {
            istruzione = scannerDiLinee.nextLine();
        } while (!processaIstruzione(istruzione));
    }   

    /**
     * Processa una istruzione 
     *
     * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
     */
    private boolean processaIstruzione(String istruzione) {
        Comando comandoDaEseguire = new Comando(istruzione);

        if (comandoDaEseguire.getNome() == null) {
            System.out.println("Comando mancante");
        }
        else if (comandoDaEseguire.getNome().equals("fine")) {
            this.fine(); 
            return true;
        }
        else if (comandoDaEseguire.getNome().equals("vai"))
            this.giocatore.vai(comandoDaEseguire.getParametro());
        else if (comandoDaEseguire.getNome().equals("aiuto"))
            this.aiuto();
        else if (comandoDaEseguire.getNome().equals("guarda"))
            System.out.println(this.giocatore.borsaString());
        else if (comandoDaEseguire.getNome().equals("prendi"))
            this.giocatore.prendi(comandoDaEseguire.getParametro());
        else if (comandoDaEseguire.getNome().equals("posa"))
            this.giocatore.posa(comandoDaEseguire.getParametro());
        else
            System.out.println("Comando sconosciuto");

        System.out.println(this.giocatore.getStanzaCorrente().getDescrizione());

        if (isFinita()) {
            System.out.println("Fine Partita");
            if (this.vinta()) System.out.println("Hai vinto!");
            return true;
        }
        else return false;
    }

    // implementazioni dei comandi dell'utente:

    /**
     * Stampa informazioni di aiuto.
     */
    private void aiuto() {
        for(int i=0; i< elencoComandi.length; i++) 
            System.out.print(elencoComandi[i]+" ");
        System.out.println();
    }

    /**
     * Comando "Fine".
     */
    private void fine() {
        System.out.println("Grazie di aver giocato!");  // si desidera smettere
    }

>>>>>>> 2db0dea (Aggiunte altre classi)
    /**
     * Restituisce vero se e solo se la partita e' stata vinta
     * @return vero se partita vinta
     */
    public boolean vinta() {
<<<<<<< HEAD
        return this.getStanzaCorrente()== this.getStanzaVincente();
=======
        return this.giocatore.getStanzaCorrente()== this.labirinto.getStanzaVincente();
>>>>>>> 2db0dea (Aggiunte altre classi)
    }

    /**
     * Restituisce vero se e solo se la partita e' finita
     * @return vero se partita finita
     */
    public boolean isFinita() {
<<<<<<< HEAD
        return finita || vinta() || (cfu == 0);
=======
        return finita || vinta() || this.giocatore.hasZeroCfu();
>>>>>>> 2db0dea (Aggiunte altre classi)
    }

    /**
     * Imposta la partita come finita
     *
     */
    public void setFinita() {
        this.finita = true;
    }

<<<<<<< HEAD
    public int getCfu() {
        return this.cfu;
    }

    public void setCfu(int cfu) {
        this.cfu = cfu;        
    }    
=======
>>>>>>> 2db0dea (Aggiunte altre classi)
}
