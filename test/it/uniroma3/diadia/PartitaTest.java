package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * test della classe partita 
 */
class PartitaTest {
	Partita partita;
	Giocatore giocatore;
    Labirinto labirinto;
	
	@BeforeEach
	void setUp() {
        // Setup del test, viene eseguito prima di ogni test.
        IOConsole ioconsole = new IOConsole();
		this.partita     = new Partita(ioconsole);
		this.giocatore   = this.partita.getGiocatore();
        this.labirinto   = this.partita.getLabirinto();
	}

	@Test
	// verifica che la partita non è finita prima di cominciare
	void verificaisFinita() {
		assertFalse(this.partita.isFinita(), "La partita è finita prima di cominciare");
	}
	
    @Test
    public void testInizializzazionePartita() {
        // Verifica che la partita sia inizializzata correttamente
        assertFalse(this.partita.isFinita(), "La partita non dovrebbe essere finita all'inizio");
    }

    @Test
    public void testVittoria() {
        // Impostiamo la stanza corrente su quella finale e verifichiamo la vittoria
        this.giocatore.setStanzaCorrente(this.labirinto.getStanzaVincente());
        assertTrue(this.partita.vinta(), "La partita dovrebbe essere vinta se il giocatore è nella stanza finale");
    }

    @Test
    public void testFinePartitaConCfuZero() {
        // Impostiamo i CFU del giocatore a 0 e verifichiamo che la partita sia finita
    	for (int i=0; i < 20; i++) giocatore.togliUnCfu();
        assertTrue(partita.isFinita(), "La partita dovrebbe essere finita se i CFU del giocatore sono 0");
    }

    @Test
    public void testImpostazioneStanzaCorrente() {
        // Verifica che la stanza corrente possa essere impostata correttamente
        Stanza nuovaStanza = new Stanza("Sala della magia");
        giocatore.setStanzaCorrente(nuovaStanza);
        assertEquals("Sala della magia", giocatore.getStanzaCorrente().getNome(), "La stanza corrente non è impostata correttamente");
    }

    @Test
    public void testSetFinita() {
        // Verifica che la partita possa essere terminata manualmente
        partita.setFinita();
        assertTrue(partita.isFinita(), "La partita dovrebbe essere finita dopo aver chiamato setFinita");
    }
}
