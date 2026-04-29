package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendiTest {
    Partita partita;
    IO ioconsole;

    @BeforeEach
    public void setup() {
        //crea una console (il comando prendi ha bisogno di una console per mostrare gli errori)
        ioconsole      = new IOConsole();

        //crea partita con inclusi labirinto, giocatore, borsa (il comando posa lavora su una partita)
        this.partita      = new Partita(ioconsole);
    }

    @Test
    public void testPosaUnAttrezzo() {
        //crea un posabile
        Attrezzo attrezzo = new Attrezzo("attrezzo test", 5);
        this.partita.getGiocatore().getStanzaCorrente().addAttrezzo(attrezzo);

        //crea un comando di prenditura
        Comando com_prendi   = new ComandoPrendi();
        com_prendi.setIOConsole(ioconsole);
        com_prendi.setParametro("attrezzo test");

        com_prendi.esegui(partita);
        assertTrue(partita.getGiocatore().hasAttrezzo("attrezzo test"), "non sono riuscito a prendere l'attrezzo dalla stanza");
    }
}
