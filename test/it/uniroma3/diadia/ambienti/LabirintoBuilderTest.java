package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * test della classe Labirinto
 */

public class LabirintoBuilderTest {
    LabirintoBuilder labirintoBuilder;
    @BeforeEach
    public void setUp() {
        labirintoBuilder = new LabirintoBuilder();
    }

    @Test
    public void addStanzaTest() {
        labirintoBuilder.addStanza("prova");
    }

    @Test
    public void addStanzaInizialeTest() {
        labirintoBuilder.addStanzaIniziale("iniziale");
        String actual = labirintoBuilder.getLabirinto().getStanzaIniziale().getNome();
        assertEquals(actual, "iniziale", "Stanza iniziale mal creata");
    }
    
    @Test
    public void addStanzaVincenteTest() {
        labirintoBuilder.addStanzaVincente("vincente");
        labirintoBuilder.addStanzaTest("vincente");
        assertEquals(true, labirintoBuilder.getStanzaTest().isVincente, "Stanza iniziale mal creata");
    }

    @Test
    public void addAdiacenzaTest() {
        labirintoBuilder.addAdiacenza("partenza", "arrivo", Direzione.EST);
        labirintoBuilder.addStanzaTest("partenza");
        String actual = labirintoBuilder.getStanzaTest().getStanzaAdiacente(Direzione.EST).getNome();
        assertEquals(actual, "arrivo", "Stanza di arrivo spostamento mal impostata");
    }

    @Test
    public void addAttrezzoTest() {
        labirintoBuilder.addAttrezzo("stanzaConAttrezzo", "nomeAttrezzo", 1);
        labirintoBuilder.addStanzaTest("stanzaConAttrezzo");
        Boolean actual = labirintoBuilder.getStanzaTest().hasAttrezzo("nomeAttrezzo");
        assertTrue(actual, "Non vedo l'attrezzo nella stanza");
    }

}
