package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

public class ComandoPrendiTest {
    Partita partita;

    @BeforeEach
    public void setup() {
        //crea una console (il comando posa ha bisogno di una console per mostrare gli errori)
        IO ioconsole = new IOConsole();

        //crea un labirinto
        Labirinto.LabirintoBuilder builder = new Labirinto.LabirintoBuilder();
        Labirinto labirinto = builder.creaLabirintoBase().getLabirinto();

        //crea un giocatore
        Giocatore giocatore = new Giocatore(ioconsole, labirinto.getStanzaIniziale());

        //crea partita con inclusi giocatore, borsa (il comando posa lavora su una partita)
        partita = new Partita(ioconsole, giocatore);
    }

    @Test
    public void testPosaUnAttrezzo() {
        //crea un posabile
        Attrezzo attrezzo = new Attrezzo("attrezzo test", 5);
        this.partita.getGiocatore().getStanzaCorrente().addAttrezzo(attrezzo);

        //crea un comando di prenditura
        Comando com_prendi   = new ComandoPrendi();
        com_prendi.setIOConsole(new IOConsole());
        com_prendi.setParametro("attrezzo test");

        com_prendi.esegui(partita);
        assertTrue(partita.getGiocatore().hasAttrezzo("attrezzo test"), "non sono riuscito a prendere l'attrezzo dalla stanza");
    }
}
