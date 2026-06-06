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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Personaggio;

public class Stanza {

    private String nome;

    private HashMap<String, Attrezzo> attrezzi;

    private HashMap<Direzione, Stanza> stanzeAdiacenti;

    private Personaggio personaggio;

    public boolean isVincente;

    public void assertNomeStanzaNotNull(String nomeStanza) {
        if(nomeStanza == null) {
            throw new RuntimeException("il nome stanza è obbligatorio, impossibile continuare");
        }
    }

    public void assertStanzaNotNull(Stanza stanza) {
        if(stanza == null) {
            throw new RuntimeException("il parametro stanza è obbligatorio, impossibile continuare");
        }
    }


    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nomeStanza il nome della stanza
     */
    public Stanza(String nomeStanza) {
        assertNomeStanzaNotNull(nomeStanza);
        this.nome = nomeStanza;
        this.stanzeAdiacenti = new HashMap<>();
        this.attrezzi = new HashMap<>();
        this.isVincente = false;
    }

    /**
     * Imposta una stanza adiacente.
     *
     * @param direzione direzione in cui sara' posta la stanza adiacente.
     * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
     */
    public void impostaStanzaAdiacente(Direzione direzione, Stanza stanza) {
        if (stanza == null) stanzeAdiacenti.remove(direzione);
        stanzeAdiacenti.put(direzione, stanza);
    }

    /*
    * overload necessario per far funzionare CaricatoreLabirintoCrescenzi
    */
    public void impostaStanzaAdiacente(String nomeDirezione, Stanza stanza) {
        impostaStanzaAdiacente(Direzione.valueOf(nomeDirezione.toUpperCase()), stanza);
    }

    /**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     */
    public Stanza getStanzaAdiacente(Direzione direzione) {
        return stanzeAdiacenti.getOrDefault(direzione, null);
    }

    /*
    * overload necessario per far funzionare LabirintoBuilderTestCrescenzi
    */
    public Stanza getStanzaAdiacente(String nomeDirezione) {
        return getStanzaAdiacente(Direzione.valueOf(nomeDirezione.toUpperCase()));
    }

    /*
    * funzione non di produzione, necessaria per far funzionare LabirintoBuilderTestCrescenzi
    */
    public ArrayList<String> getDirezioni() {
        ArrayList<String> nomiDirezioniValide = new ArrayList<String>();
        for (Direzione d : stanzeAdiacenti.keySet()) {
            nomiDirezioniValide.add(d.toString().toLowerCase());
        }
        return nomiDirezioniValide;
    }

    /*
    * funzione non di produzione, necessaria per far funzionare LabirintoBuilderTestCrescenzi
    */
    public Map<String, Stanza> getMapStanzeAdiacenti() {
        HashMap<String, Stanza> mapNomeDirezioneStanzaAdiacente = new HashMap<String, Stanza>();
        for (Map.Entry<Direzione,Stanza> entry : stanzeAdiacenti.entrySet()) {
            mapNomeDirezioneStanzaAdiacente.put(entry.getKey().toString().toLowerCase(), entry.getValue());
        }
        return mapNomeDirezioneStanzaAdiacente;
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
        return attrezzi.get(nomeAttrezzo);
    }

    /**
     * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
     * @param nomeAttrezzo
     * @return true se l'attrezzo e' stato rimosso, false altrimenti
     */
    public void removeAttrezzo(String nomeAttrezzo) {
        attrezzi.remove(nomeAttrezzo);
    }

    public boolean hasPersonaggio() {
        return this.personaggio != null;
    }
    
    public Personaggio getPersonaggio() {
        return this.personaggio;
    }

    public void setPersonaggio(Personaggio personaggio) {
        this.personaggio = personaggio;
    }

    public boolean isEmpty() {
        return attrezzi.isEmpty();
    }

    /*
    metodo getattrezzi, utile SOLO alla classe LabirintoBuilderTestCrescenzi
    */
    public List<Attrezzo> getAttrezzi() {
        return new ArrayList<Attrezzo>(attrezzi.values());
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
        for (Direzione direzioneDisp : stanzeAdiacenti.keySet())
            s.append(direzioneDisp.toString().toLowerCase() + " ");
        s.append("\n");

        if (!this.isEmpty()) {
            s.append("Attrezzi nella stanza: ");
            for (Attrezzo at : attrezzi.values())
                s.append(at.toString() + " ");
        } else
            s.append("Nessun attrezzo nella stanza");


        return s.toString();
    }

    /*
    * metodo equals non necessario, aggiunto per evitare guasti e per far funzionare LabirintoBuilderTestCrescenzi
    */
    public boolean equals(Object o) {
        if (!(o instanceof Stanza)) return false;
        Stanza st = (Stanza) o;
        return this.nome.equals(st.getNome());
    }
    public int hashCode() {
        return this.nome.hashCode();
    }
}
