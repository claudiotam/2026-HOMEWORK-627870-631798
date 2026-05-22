/**
 * Questa classe costruisce poco a poco un labirito
 * lo stato di costruzione parziale è all'interno della classe,
 * non è accessibile dall'esterno
 *
 * @author Claudio Tam
 * @see 
 * @version base
 */


package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder {
    private Labirinto labirinto;
    private Map<String, Stanza> stanze;

    public LabirintoBuilder() {
        labirinto = new Labirinto();
        stanze = new HashMap<>();
    }

    private Stanza trovaStanza(String nomeStanza) {
        if(nomeStanza == null) {
            throw new RuntimeException("il nome stanza è obbligatorio, impossibile continuare");
        }
        if (stanze.containsKey(nomeStanza)) return stanze.get(nomeStanza);
        Stanza stanza = new Stanza(nomeStanza);
        stanze.put(nomeStanza, stanza);
        return stanza;
    }

    public LabirintoBuilder addStanza(String nomeStanza) {
        trovaStanza(nomeStanza);
        return this;
    }

    public LabirintoBuilder addStanzaIniziale(String nomeStanza) {
        Stanza stanza = trovaStanza(nomeStanza);
        labirinto.setStanzaIniziale(stanza);
        return this;
    }

    public LabirintoBuilder addStanzaVincente(String nomeStanza) {
        Stanza stanza = trovaStanza(nomeStanza);
        labirinto.setStanzaVincente(stanza);
        return this;
    }
    public LabirintoBuilder addAdiacenza(String nomeStanzaDa, Direzione direzione, String nomeStanzaA) {
        Stanza stanzaDa = trovaStanza(nomeStanzaDa);
        Stanza stanzaA = trovaStanza(nomeStanzaA);
        stanzaDa.impostaStanzaAdiacente(direzione, stanzaA);
        return this;
    }
    
    public LabirintoBuilder addAttrezzo(String nomeStanza, String nomeAttrezzo, int peso) {
        Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
        Stanza stanza = trovaStanza(nomeStanza);
        stanza.addAttrezzo(attrezzo);
        return this;
    }
    
    public Labirinto getLabirinto() {
        return this.labirinto;
    }
    
    public Map<String, Stanza> getListaStanze() {
        //lista?!?
        return stanze;
    }
}
