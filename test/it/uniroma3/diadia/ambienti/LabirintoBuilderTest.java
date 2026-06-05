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
        String actual = labirintoBuilder.getLabirinto().getStanzaVincente().getNome();
        assertEquals(actual, "vincente", "Stanza iniziale mal creata");
    }

    @Test
    public void addAdiacenzaTest() {
        labirintoBuilder.addAdiacenza("partenza", "arrivo", Direzione.EST);
        labirintoBuilder.addStanzaVincente("partenza");
        String actual = labirintoBuilder.getLabirinto().getStanzaVincente().getStanzaAdiacente(Direzione.EST).getNome();
        assertEquals(actual, "arrivo", "Stanza di arrivo spostamento mal impostata");
    }

    @Test
    public void addAttrezzoTest() {
        labirintoBuilder.addAttrezzo("stanzaConAttrezzo", "nomeAttrezzo", 1);
        labirintoBuilder.addStanzaVincente("stanzaConAttrezzo");
        Boolean actual = labirintoBuilder.getLabirinto().getStanzaVincente().hasAttrezzo("nomeAttrezzo");
        assertTrue(actual, "Non vedo l'attrezzo nella stanza");
    }

}
