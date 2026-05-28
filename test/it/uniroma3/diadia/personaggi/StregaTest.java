package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StregaTest {
    private Partita   partita;
    private Strega    strega;

    @BeforeEach
    void setUp() {
        // Setup del test, viene eseguito prima di ogni test.
        IOConsole ioconsole = new IOConsole();
        
        strega = new Strega(ioconsole);

        Labirinto labirinto = new Labirinto();
        Stanza stanzaunica = new Stanza("stanzaunica");
        stanzaunica.setPersonaggio(strega);
        labirinto.setStanzaIniziale(stanzaunica);
        
        this.partita = new Partita(ioconsole, labirinto);
    }

    @Test
    void testRiceviRegalo() {
        String nomeAttrezzo = "inutile";
        Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, 1);
        strega.riceviRegalo(partita, attrezzo);
        Stanza stanzaCorrente = partita.getGiocatore().getStanzaCorrente();

        assertFalse(stanzaCorrente.hasAttrezzo(nomeAttrezzo), "lattrezzo non dovrebbe essere in stanza");
    }
}
