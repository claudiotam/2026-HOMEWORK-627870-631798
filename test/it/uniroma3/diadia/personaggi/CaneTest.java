package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

public class CaneTest {
    private Partita   partita;
    private Cane      cane;

    @BeforeEach
    void setUp() {
        // Setup del test, viene eseguito prima di ogni test.
        IOConsole ioconsole = new IOConsole();
        
        cane = new Cane(ioconsole);

        Labirinto.LabirintoBuilder builder = new Labirinto.LabirintoBuilder();
        builder.addStanzaIniziale("stanzaunica");
        Labirinto labirinto = builder.getLabirinto();
        labirinto.getStanzaIniziale().setPersonaggio(cane);

        Giocatore giocatore = new Giocatore(ioconsole, labirinto.getStanzaIniziale());

        this.partita = new Partita(ioconsole, giocatore);
    }

    @Test
    void testRiceviRegaloCattivo() {
        Giocatore giocatore = partita.getGiocatore();
        int cfuIniziali = giocatore.getCfu();
        Stanza stanzaCorrente = giocatore.getStanzaCorrente();
        String nomeAttrezzo = "regalo_cattivo";

        cane.riceviRegalo(partita, new Attrezzo(nomeAttrezzo, 1));
        assertEquals(cfuIniziali-1, giocatore.getCfu(), "il cane non ha tolto esattamente un cfu");
        assertTrue(stanzaCorrente.hasAttrezzo(nomeAttrezzo), "il regalo cattivo dovrebbe essere in stanza");

        cane.riceviRegalo(partita, new Attrezzo(nomeAttrezzo, 1));
        assertTrue(stanzaCorrente.hasAttrezzo(nomeAttrezzo), "il regalo cattivo dovrebbe essere in stanza");
    }

    @Test
    void testRiceviRegaloBuono() {
        Giocatore giocatore = partita.getGiocatore();
        int cfuIniziali = giocatore.getCfu();
        Stanza stanzaCorrente = giocatore.getStanzaCorrente();
        String nomeAttrezzo = "cibo_per_cani";

        cane.riceviRegalo(partita, new Attrezzo(nomeAttrezzo, 1));
        assertEquals(cfuIniziali+1, giocatore.getCfu(), "il cane non ha aggiunto esattamente un cfu");
        assertFalse(stanzaCorrente.hasAttrezzo(nomeAttrezzo), "il cibo non dovrebbe essere in stanza");

        cane.riceviRegalo(partita, new Attrezzo(nomeAttrezzo, 1));
        assertEquals(cfuIniziali+2, giocatore.getCfu(), "il cane non ha aggiunto esattamente due cfu");
        assertFalse(stanzaCorrente.hasAttrezzo(nomeAttrezzo), "il cibo non dovrebbe essere in stanza");
    }
}
