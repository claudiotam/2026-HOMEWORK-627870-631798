package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

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
        //crea una console (il comando posa ha bisogno di una console per mostrare gli errori)
        IO ioconsole = new IOConsole();

        //crea un labirinto
        labirinto = new Labirinto();
        labirinto.creaLabirintoBase();

        //crea un giocatore
        giocatore = new Giocatore(ioconsole, labirinto.getStanzaIniziale());

        //crea partita con inclusi giocatore, borsa (il comando posa lavora su una partita)
        partita = new Partita(ioconsole, giocatore);
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
        // Impostiamo la stanza corrente come vincente e verifichiamo la vittoria
        this.giocatore.getStanzaCorrente().isVincente = true;
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
        List<String> messaggi_in = List.of("fine");
        
        //crea una console di test
        IOSimulator iosimulator = new IOSimulator(messaggi_in);

        //crea il labirinto base
        //Labirinto labirinto = new Labirinto();
        //labirinto.creaLabirintoBase();

        //crea partita
        Partita partita_sim = new Partita(iosimulator, giocatore);

        //lancia
        partita_sim.gioca();

        //scarica i messaggi da ricevere
        List<String> messaggiOut = iosimulator.getMessaggiOut();

        //verifica che sono stati prodotti 4 messaggi
        assertEquals(messaggiOut.size(), 2);

        //verifica che l'ultimo messaggio prodotto è corretto
        assertEquals(messaggiOut.getLast(), "Grazie di aver giocato!");
    }

    @Test
    public void testPartitaEsaurimentoCfu() {
        //prepara dei messaggi da inviare
        List<String> messaggiIn = List.of();
        
        //crea una console di test
        IOSimulator iosimulator = new IOSimulator(messaggiIn);

        //crea un labirinto
        //Labirinto labirinto = new Labirinto();
        //labirinto.creaLabirintoBase();

        //crea partita
        Partita partita_sim = new Partita(iosimulator, giocatore);

        //modifica i cfu giocatore
        partita_sim.getGiocatore().setCfu(36);

        //lancia
        partita_sim.gioca();

        //scarica i messaggi
        List<String> messaggiOut = iosimulator.getMessaggiOut();

        //verifica che sono stati prodotti 39 messaggi
        assertEquals(messaggiOut.size(), 39);

        //verifica che il penultimo messaggio prodotto è corretto
        assertTrue(messaggiOut.getLast().contains("giocato"), "l'ultimo messaggio dovrebbe contenere la parola 'giocato'");

    }
}
