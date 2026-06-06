package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;

/**
 * test della classe Labirinto
 */

public class LabirintoTest {
    private Labirinto labirinto;
    private Stanza stanzaIniziale;

    @BeforeEach
    public void setUp() {
        // Setup per ogni test, viene eseguito prima di ciascun test
    	// Crea un nuovo labirinto
        Labirinto.LabirintoBuilder builder = new Labirinto.LabirintoBuilder();
        this.labirinto = builder.creaLabirintoBase().getLabirinto();
        this.stanzaIniziale = this.labirinto.getStanzaIniziale();
    }

    @Test
    void VincenteNordIniziale() {
        assertTrue(this.stanzaIniziale.getStanzaAdiacente(Direzione.NORD).isVincente);
    }
    
    @Test
    public void testStanzaCorrente() {
        // Verifica che la stanza corrente (all'inizio, la stanza iniziale) sia corretta
        assertNotNull(this.stanzaIniziale, "La stanza iniziale non dovrebbe essere null");
        assertEquals("Atrio", this.stanzaIniziale.getNome(), "La stanza iniziale dovrebbe essere 'Atrio'");
    }

    @Test
    public void BibliotecaNordIniziale() {
        // Verifica che la stanza vincente sia corretta
        Stanza stanzaNord = this.stanzaIniziale.getStanzaAdiacente(Direzione.NORD);
        assertNotNull(stanzaNord, "La stanza vincente non dovrebbe essere null");
        assertEquals("Biblioteca", stanzaNord.getNome(), "La stanza nord dovrebbe essere 'Biblioteca'");
    }
   
    @Test
    public void testCollegamentoTraStanzeAtrio() {
        // Verifica la correttezza dei collegamenti tra le stanze Atrio
        Stanza atrio      = this.stanzaIniziale;
        Stanza atrio_est  = atrio.getStanzaAdiacente(Direzione.EST);
        Stanza atrio_sud  = atrio.getStanzaAdiacente(Direzione.SUD);
        Stanza atrio_nord = atrio.getStanzaAdiacente(Direzione.NORD);
        
        assertNotNull(atrio_nord, "Atrio dovrebbe avere una stanza adiacente a nord");
        assertEquals("Biblioteca", atrio_nord.getNome(), "La stanza adiacente a nord dell'Atrio dovrebbe essere 'Biblioteca'");

        assertNotNull(atrio_est, "Atrio dovrebbe avere un collegamento verso est");
        assertEquals("Aula N11", atrio_est.getNome(), "Atrio dovrebbe avere un collegamento verso est a Aula N11");

        assertNotNull(atrio_sud, "Atrio dovrebbe avere un collegamento verso sud");
        assertEquals("Magazzino", atrio_sud.getNome(), "Atrio dovrebbe avere un collegamento verso Magazzino");
            }

    @Test
    public void testCollegamentoTraStanzeNonAtrio() {
        // Verifica la correttezza dei collegamenti tra le stanze non Atrio
        Stanza aulaN10 = this.stanzaIniziale.getStanzaAdiacente(Direzione.SUD);
        Stanza n10_ovest = aulaN10.getStanzaAdiacente(Direzione.OVEST);

        assertNotNull(n10_ovest, "Aula N10 dovrebbe avere un collegamento verso ovest");
        assertEquals("LabIA", n10_ovest.getNome(), "Aula N10 dovrebbe avere un collegamento verso ovest a LabIA");
    }

    @Nested
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

}
