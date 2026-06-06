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
    private Map<String, Stanza> nome2stanza;
    private String nomeUltimaStanzaTrovata;
    private Stanza stanzaTest;

    public LabirintoBuilder() {
        labirinto = new Labirinto();
        nome2stanza = new HashMap<>();
    }

    public void addStanzaTest(String nomeStanza) {
        this.stanzaTest = trovaStanza(nomeStanza);
    }

    public Stanza getStanzaTest() {
        return this.stanzaTest;
    }

    private Stanza trovaStanza(String nomeStanza) {
        if(nomeStanza == null) {
            throw new RuntimeException("il nome stanza è obbligatorio, impossibile continuare");
        }
        nomeUltimaStanzaTrovata = nomeStanza;
        if (nome2stanza.containsKey(nomeStanza)) {
            return nome2stanza.get(nomeStanza);
        }
        else {
            Stanza stanza = new Stanza(nomeStanza);
            nome2stanza.put(nomeStanza, stanza);
            return stanza;
        }
    }

    public LabirintoBuilder addStanza(String nomeStanza) {
        trovaStanza(nomeStanza);
        return this;
    }

    public LabirintoBuilder addStanzaMagica(String nomeStanzaMagica, int numeroPosature) {
        if (nome2stanza.containsKey(nomeStanzaMagica)) {
            throw new RuntimeException("non puoi creare una stanza magica con un nome già creato");
        }
        else {
            nomeUltimaStanzaTrovata = nomeStanzaMagica;
            Stanza stanza = new StanzaMagica(nomeStanzaMagica, numeroPosature);
            nome2stanza.put(nomeStanzaMagica, stanza);
            return this;
        }
    }

    public LabirintoBuilder addStanzaBloccata(String nomeStanzaBloccata, String nomeDirezione, String nomeAttrezzoAntiblocco) {
        if (nome2stanza.containsKey(nomeStanzaBloccata)) {
            throw new RuntimeException("non puoi creare una stanza bloccata con un nome già creato");
        }
        else {
            nomeUltimaStanzaTrovata = nomeStanzaBloccata;
            Stanza stanza = new StanzaBloccata(nomeStanzaBloccata, nomeAttrezzoAntiblocco, Direzione.valueOf(nomeDirezione.toUpperCase()));
            nome2stanza.put(nomeStanzaBloccata, stanza);
            return this;
        }
    }

    public LabirintoBuilder addStanzaBuia(String nomeStanzaBuia, String nomeAttrezzoAntibuio) {
        if (nome2stanza.containsKey(nomeStanzaBuia)) {
            throw new RuntimeException("non puoi creare una stanza buia con un nome già creato");
        }
        else {
            nomeUltimaStanzaTrovata = nomeStanzaBuia;
            Stanza stanza = new StanzaBuia(nomeStanzaBuia, nomeAttrezzoAntibuio);
            nome2stanza.put(nomeStanzaBuia, stanza);
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
        return nome2stanza;
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
