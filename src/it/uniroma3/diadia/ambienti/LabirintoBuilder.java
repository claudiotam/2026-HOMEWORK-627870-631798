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
    private String nomeUltimaStanzaTrovata;

    public LabirintoBuilder() {
        labirinto = new Labirinto();
        stanze = new HashMap<>();
    }

    private Stanza trovaStanza(String nomeStanza) {
        if(nomeStanza == null) {
            throw new RuntimeException("il nome stanza è obbligatorio, impossibile continuare");
        }
        nomeUltimaStanzaTrovata = nomeStanza;
        if (stanze.containsKey(nomeStanza)) {
            return stanze.get(nomeStanza);
        }
        else {
            Stanza stanza = new Stanza(nomeStanza);
            stanze.put(nomeStanza, stanza);
            return stanza;
        }
    }

    public LabirintoBuilder addStanza(String nomeStanza) {
        trovaStanza(nomeStanza);
        return this;
    }

    public LabirintoBuilder addStanzaMagica(String nomeStanzaMagica, int numeroPosature) {
        if (stanze.containsKey(nomeStanzaMagica)) {
            throw new RuntimeException("non puoi creare una stanza magica con un nome già creato");
        }
        else {
            nomeUltimaStanzaTrovata = nomeStanzaMagica;
            Stanza stanza = new StanzaMagica(nomeStanzaMagica, numeroPosature);
            stanze.put(nomeStanzaMagica, stanza);
            return this;
        }
    }

    public LabirintoBuilder addStanzaBloccata(String nomeStanzaBloccata, String nomeDirezione, String nomeAttrezzoAntiblocco) {
        if (stanze.containsKey(nomeStanzaBloccata)) {
            throw new RuntimeException("non puoi creare una stanza bloccata con un nome già creato");
        }
        else {
            nomeUltimaStanzaTrovata = nomeStanzaBloccata;
            Stanza stanza = new StanzaBloccata(nomeStanzaBloccata, nomeAttrezzoAntiblocco, Direzione.valueOf(nomeDirezione.toUpperCase()));
            stanze.put(nomeStanzaBloccata, stanza);
            return this;
        }
    }

    public LabirintoBuilder addStanzaBuia(String nomeStanzaBuia, String nomeAttrezzoAntibuio) {
        if (stanze.containsKey(nomeStanzaBuia)) {
            throw new RuntimeException("non puoi creare una stanza buia con un nome già creato");
        }
        else {
            nomeUltimaStanzaTrovata = nomeStanzaBuia;
            Stanza stanza = new StanzaBuia(nomeStanzaBuia, nomeAttrezzoAntibuio);
            stanze.put(nomeStanzaBuia, stanza);
            return this;
        }
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

    public LabirintoBuilder addAdiacenza(String nomeStanzaDa, String nomeStanzaA, Direzione direzione) {
        Stanza stanzaDa = trovaStanza(nomeStanzaDa);
        Stanza stanzaA = trovaStanza(nomeStanzaA);
        stanzaDa.impostaStanzaAdiacente(direzione, stanzaA);
        return this;
    }
    /*
    * overload per supportare la classe selvaggia LabirintoBuilderTestCrescenzi
    */
    public LabirintoBuilder addAdiacenza(String nomeStanzaDa, String nomeStanzaA, String nomeDirezione) {
        return addAdiacenza(nomeStanzaDa, nomeStanzaA, Direzione.valueOf(nomeDirezione.toUpperCase()));
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

    /*
    * metodi che ricordano l'ultima stanza, rompono la prevedibilità della funzione
    */
    public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso) {
        Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
        Stanza stanza = trovaStanza(nomeUltimaStanzaTrovata);
        stanza.addAttrezzo(attrezzo);
        return this;
    }

}
