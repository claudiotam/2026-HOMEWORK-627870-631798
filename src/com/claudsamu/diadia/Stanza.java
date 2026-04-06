/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author Claudio Tam
 * @see Attrezzo
 * @version base
 */

package com.claudsamu.diadia;

public class Stanza {

    static final private int NUMERO_MASSIMO_DIREZIONI = 4;
    static final private int NUMERO_MASSIMO_ATTREZZI = 10;

    private String nome;

    private Attrezzo[] attrezzi;
    private int numeroAttrezzi;

    private Stanza[] stanzeAdiacenti;
    private int numeroStanzeAdiacenti;

    private String[] direzioni;

    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
    public Stanza(String nome) {
        this.nome = nome;
        this.numeroStanzeAdiacenti = 0;
        this.numeroAttrezzi = 0;
        this.direzioni = new String[NUMERO_MASSIMO_DIREZIONI];
        this.stanzeAdiacenti = new Stanza[NUMERO_MASSIMO_DIREZIONI];
        this.attrezzi = new Attrezzo[NUMERO_MASSIMO_ATTREZZI];
    }

    /**
     * Imposta una stanza adiacente.
     *
     * @param direzione direzione in cui sara' posta la stanza adiacente.
     * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
     */
    public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
        boolean aggiornato = false;
        for(int i=0; i<this.direzioni.length; i++)
            if (direzione.equals(this.direzioni[i])) {
                this.stanzeAdiacenti[i] = stanza;
                aggiornato = true;
            }
        if (!aggiornato)
            if (this.numeroStanzeAdiacenti < NUMERO_MASSIMO_DIREZIONI) {
                this.direzioni[numeroStanzeAdiacenti] = direzione;
                this.stanzeAdiacenti[numeroStanzeAdiacenti] = stanza;
                this.numeroStanzeAdiacenti++;
            }
    }

    /**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     */
    public Stanza getStanzaAdiacente(String direzione) {
        Stanza stanza = null;
        for(int i=0; i<this.numeroStanzeAdiacenti; i++)
            if (this.direzioni[i].equals(direzione))
                stanza = this.stanzeAdiacenti[i];
        return stanza;
    }

    /**
     * Restituisce la nome della stanza.
     * @return il nome della stanza
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Restituisce la descrizione della stanza.
     * @return la descrizione della stanza
     */
    public String getDescrizione() {
        return this.toString();
    }

    /**
     * Restituisce la collezione di attrezzi presenti nella stanza.
     * @return la collezione di attrezzi nella stanza.
     */
    public Attrezzo[] getAttrezzi() {
        return this.attrezzi;
    }

    /**
<<<<<<< HEAD
=======
     * Controlla se un oggetto puà entrare in una stanza.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se è possibile aggiungere l'attrezzo, false atrimenti.
     */
    public boolean acceptsAttrezzo(Attrezzo attrezzo) {
        if (this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI) return true;
        else return false;
    }

    /**
>>>>>>> 2db0dea (Aggiunte altre classi)
     * Mette un attrezzo nella stanza.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
        if (this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI) {
            this.attrezzi[numeroAttrezzi] = attrezzo;
            this.numeroAttrezzi++;
            return true;
        }
        else {
            return false;
        }
    }

    /**
<<<<<<< HEAD
     * Restituisce una rappresentazione stringa di questa stanza,
     * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
     * @return la rappresentazione stringa
     */
    public String toString() {
        StringBuilder risultato = new StringBuilder();
        risultato.append("Ti trovi qui: \n");
        risultato.append(this.nome);
        risultato.append("\nUscite: ");
        for (String direzione : this.direzioni)
            if (direzione!=null)
                risultato.append(direzione+" ");
        risultato.append("\nAttrezzi nella stanza: ");
        boolean nessunAttrezzo = false;
        for (Attrezzo attrezzo : this.attrezzi) {
            if(attrezzo!=null) {
                nessunAttrezzo = true;
                risultato.append(attrezzo.toString()+" ");
            }
        }
        if(!nessunAttrezzo)
            risultato.append("nessun attrezzo");

        risultato.append("\n");
        return risultato.toString();
    }
    /**
=======
>>>>>>> 2db0dea (Aggiunte altre classi)
     * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
     * @return true se l'attrezzo esiste nella stanza, false altrimenti.
     */
    public boolean hasAttrezzo(String nomeAttrezzo) {
<<<<<<< HEAD
        boolean trovato;
        trovato = false;
        for (Attrezzo attrezzo : this.attrezzi) {
            if (attrezzo.getNome().equals(nomeAttrezzo))
                trovato = true;
        }
        return trovato;
=======
        return this.getAttrezzo(nomeAttrezzo) != null;
>>>>>>> 2db0dea (Aggiunte altre classi)
    }

    /**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
     * @param nomeAttrezzo
     * @return l'attrezzo presente nella stanza.
     *            null se l'attrezzo non e' presente.
     */
    public Attrezzo getAttrezzo(String nomeAttrezzo) {
<<<<<<< HEAD
        Attrezzo attrezzoCercato;
        attrezzoCercato = null;
        for (Attrezzo attrezzo : this.attrezzi) {
            if (attrezzo.getNome().equals(nomeAttrezzo))
                attrezzoCercato = attrezzo;
        }
        return attrezzoCercato;    
=======
        for (int i = 0; i < this.numeroAttrezzi; i++) {
            if (attrezzi[i].getNome().equals(nomeAttrezzo)) {
                return attrezzi[i];
            }
        }
        return null;
>>>>>>> 2db0dea (Aggiunte altre classi)
    }

    /**
     * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
     * @param nomeAttrezzo
     * @return true se l'attrezzo e' stato rimosso, false altrimenti
     */
<<<<<<< HEAD
    public boolean removeAttrezzo(Attrezzo attrezzo) {
        // TODO da implementare
        return false;
=======
    public boolean removeAttrezzo(String nomeAttrezzo) {
        int w = 0;
        int r = 0;
        for (r = 0; r < this.numeroAttrezzi; r++) {
            if (!this.attrezzi[r].getNome().equals(nomeAttrezzo)) {
                if (r != w) this.attrezzi[w] = this.attrezzi[r];
                w++;
            }
        }
        if (r == w) return false;
        else {
            this.numeroAttrezzi = w;
            return true;
        }
>>>>>>> 2db0dea (Aggiunte altre classi)
    }


    public String[] getDirezioni() {
        String[] direzioni = new String[this.numeroStanzeAdiacenti];
        for(int i=0; i<this.numeroStanzeAdiacenti; i++)
            direzioni[i] = this.direzioni[i];
        return direzioni;
    }

<<<<<<< HEAD
=======
    /**
     * Restituisce una rappresentazione stringa di questa stanza,
     * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
     * @return la rappresentazione stringa
     */
    public String toString() {
        StringBuilder risultato = new StringBuilder();
        risultato.append("Ti trovi qui: ");
        risultato.append(this.nome);
        risultato.append("\nUscite: ");
        for (String direzione : this.direzioni)
            if (direzione!=null)
                risultato.append(direzione+" ");
        risultato.append("\nAttrezzi nella stanza: ");
        boolean qualcheAttrezzo = false;
        for (int i=0; i<this.numeroAttrezzi; i++) {
            qualcheAttrezzo = true;
            risultato.append(attrezzi[i].toString()+" ");
        }
        if(!qualcheAttrezzo)
            risultato.append("nessun attrezzo");

        risultato.append("\n");
        return risultato.toString();
    }

>>>>>>> 2db0dea (Aggiunte altre classi)
}
