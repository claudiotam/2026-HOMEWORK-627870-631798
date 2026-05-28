package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class MagoTest {
    private Partita   partita;
    private Mago      mago;

    @BeforeEach
    void setUp() {
        // Setup del test, viene eseguito prima di ogni test.
        IOConsole ioconsole = new IOConsole();
        
        mago = new Mago(ioconsole);

        Labirinto labirinto = new Labirinto();
        Stanza stanzaunica = new Stanza("stanzaunica");
        stanzaunica.setPersonaggio(mago);
        labirinto.setStanzaIniziale(stanzaunica);
        
        this.partita     = new Partita(ioconsole, labirinto);
    }

    @Test
    void testRiceviRegaloDimezzaPeso() {
        Attrezzo at = new Attrezzo("scatola", 7);
        Stanza stanzaCorrente = partita.getGiocatore().getStanzaCorrente();

        mago.riceviRegalo(partita, at);
        assertEquals(at.getPeso(), 4, "la scatola dovrebbe avere peso 4");
        assertTrue(stanzaCorrente.hasAttrezzo("scatola"), "la scatola dovrebbe essere in stanza");

        mago.riceviRegalo(partita, at);
        assertEquals(at.getPeso(), 2, "la scatola dovrebbe avere peso 2");
        assertTrue(stanzaCorrente.hasAttrezzo("scatola"), "la scatola dovrebbe essere in stanza");
    }

}
