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

package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Stanza {

    static final private int NUMERO_MASSIMO_DIREZIONI = 4;

    private String nome;

    private Map<String, Attrezzo> attrezzi;

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
        this.direzioni = new String[NUMERO_MASSIMO_DIREZIONI];
        this.stanzeAdiacenti = new Stanza[NUMERO_MASSIMO_DIREZIONI];
        this.attrezzi = new HashMap<>();
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
     * Restituisce la collezione di attrezzi presenti nella stanza.
     * @return la collezione di attrezzi nella stanza.
     */
    //public Attrezzo[] getAttrezzi() {
    //    return this.attrezzi;
    //}

    /**
     * Controlla se un oggetto puà entrare in una stanza.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se è possibile aggiungere l'attrezzo, false atrimenti.
     */
    public boolean acceptsAttrezzo(Attrezzo attrezzo) {
    	return attrezzo != null;
    }

    /**
     * Mette un attrezzo nella stanza.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
    	if (!acceptsAttrezzo(attrezzo)) return false;
        attrezzi.put(attrezzo.getNome(), attrezzo);
        return true;
    }

    /**
     * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
     * @return true se l'attrezzo esiste nella stanza, false altrimenti.
     */
    public boolean hasAttrezzo(String nomeAttrezzo) {
        return attrezzi.containsKey(nomeAttrezzo);
    }

    /**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
     * @param nomeAttrezzo
     * @return l'attrezzo presente nella stanza.
     *            null se l'attrezzo non e' presente.
     */
    public Attrezzo getAttrezzo(String nomeAttrezzo) {
        return attrezzi.getOrDefault(nomeAttrezzo, null);
    }

    /**
     * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
     * @param nomeAttrezzo
     * @return true se l'attrezzo e' stato rimosso, false altrimenti
     */
    public void removeAttrezzo(String nomeAttrezzo) {
        attrezzi.remove(nomeAttrezzo);
    }

    public boolean isEmpty() {
        return attrezzi.isEmpty();
    }

    public String[] getDirezioni() {
        String[] direzioni = new String[this.numeroStanzeAdiacenti];
        for(int i=0; i<this.numeroStanzeAdiacenti; i++)
            direzioni[i] = this.direzioni[i];
        return direzioni;
    }

    /**
     * Restituisce la descrizione della stanza.
     * @return la descrizione della stanza
     */
    /**
     * Restituisce una rappresentazione stringa di questa stanza,
     * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
     * @return la rappresentazione stringa
     */
    public String getDescrizione() {
        StringBuilder s = new StringBuilder();
        s.append("Ti trovi qui: ");
        s.append(this.nome);
        s.append("\nUscite: ");
        for (String direzione : this.direzioni)
            if (direzione!=null)
                s.append(direzione+" ");
        s.append("\n");

        if (!this.isEmpty()) {
            s.append("Attrezzi nella stanza: ");
            for (Attrezzo at : attrezzi.values())
                s.append(at.toString() + " ");
        } else
            s.append("Nessun attrezzo nella stanza");


        return s.toString();
    }

}
