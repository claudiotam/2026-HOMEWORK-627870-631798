package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * test della classe Labirinto
 */

public class LabirintoTest {
    private Labirinto labirinto;
    private Stanza stanzaIniziale;
    private Stanza stanzaVincente;

    @BeforeEach
    public void setUp() {
        // Setup per ogni test, viene eseguito prima di ciascun test
    	// Crea un nuovo labirinto
        this.labirinto = new Labirinto();
        labirinto.creaLabirintoBase();
        this.stanzaIniziale = this.labirinto.getStanzaIniziale();
        this.stanzaVincente = this.labirinto.getStanzaVincente();
    }

    @Test
    void LaStanzaVincenteEBiblioteca() {
        assertEquals("Biblioteca", this.labirinto.getStanzaVincente().getNome());
    }
    @Test
    void VincenteNordIniziale() {
        assertEquals(this.labirinto.getStanzaVincente(), this.labirinto.getStanzaIniziale().getStanzaAdiacente("nord"));
    }
    
    @Test
    public void testStanzaCorrente() {
        // Verifica che la stanza corrente (all'inizio, la stanza iniziale) sia corretta
        assertNotNull(this.stanzaIniziale, "La stanza iniziale non dovrebbe essere null");
        assertEquals("Atrio", this.stanzaIniziale.getNome(), "La stanza iniziale dovrebbe essere 'Atrio'");
    }

    @Test
    public void testStanzaVincente() {
        // Verifica che la stanza vincente sia corretta
        assertNotNull(this.stanzaVincente, "La stanza vincente non dovrebbe essere null");
        assertEquals("Biblioteca", this.stanzaVincente.getNome(), "La stanza vincente dovrebbe essere 'Biblioteca'");
    }
   
    @Test
    public void testCollegamentoTraStanzeAtrio() {
        // Verifica la correttezza dei collegamenti tra le stanze Atrio
        Stanza atrio      = this.stanzaIniziale;
        Stanza atrio_est  = atrio.getStanzaAdiacente("est");
        Stanza atrio_sud  = atrio.getStanzaAdiacente("sud");
        Stanza atrio_nord = atrio.getStanzaAdiacente("nord");
        
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
        Stanza aulaN10 = this.stanzaIniziale.getStanzaAdiacente("sud");
        Stanza n10_ovest = aulaN10.getStanzaAdiacente("ovest");

        assertNotNull(n10_ovest, "Aula N10 dovrebbe avere un collegamento verso ovest");
        assertEquals("LabIA", n10_ovest.getNome(), "Aula N10 dovrebbe avere un collegamento verso ovest a LabIA");
    }
}
