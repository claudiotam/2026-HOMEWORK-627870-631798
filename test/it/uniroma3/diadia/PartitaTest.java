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

    /*
    test con ioconsole
    */
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

    /*
    test senza ioconsole
    */
    @Test
    public void testPartitaMonocomando() {
        //prepara i messaggi da inviare
        String[] messaggi_in = new String[] {"fine"};
        
        //crea una console di test
        IOSimulator iosimulator = new IOSimulator(messaggi_in);

        //crea partita
        Partita partita_sim = new Partita(iosimulator);

        //lancia
        partita_sim.gioca();

        //scarica i messaggi da ricevere
        String[] messaggi_out = iosimulator.getMessaggiOut();
        int messaggi_out_write_cursor = iosimulator.getMessaggiOutCursor();

        //verifica che sono stati prodotti 4 messaggi
        assertEquals(messaggi_out_write_cursor, 4);

        //verifica che l'ultimo messaggio prodotto è corretto
        assertEquals(messaggi_out[messaggi_out_write_cursor-1], "Grazie di aver giocato!");
    }

    @Test
    public void testPartitaEsaurimentoCfu() {
        //prepara dei messaggi da inviare
        String[] messaggi_in = new String[] {};
        
        //crea una console di test
        IOSimulator iosimulator = new IOSimulator(messaggi_in);

        //crea partita
        Partita partita_sim = new Partita(iosimulator);

        //modifica i cfu giocatore
        partita_sim.getGiocatore().setCfu(36);

        //lancia
        partita_sim.gioca();

        //scarica i messaggi
        String[] messaggi_out = iosimulator.getMessaggiOut();
        int messaggi_out_write_cursor = iosimulator.getMessaggiOutCursor();

        //verifica che sono stati prodotti 39 messaggi
        assertEquals(messaggi_out_write_cursor, 39);

        //verifica che il penultimo messaggio prodotto è corretto
        assertTrue(messaggi_out[messaggi_out_write_cursor-2].contains("perso"), "l'ultimo messaggio dovrebbe contenere la parola 'perso'");

    }
}
