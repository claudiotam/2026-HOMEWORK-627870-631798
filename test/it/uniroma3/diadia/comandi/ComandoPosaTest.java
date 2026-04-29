package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosaTest {
    Partita partita;
    IO ioconsole;
    
    @BeforeEach
    public void setup() {
        //crea una console (il comando posa ha bisogno di una console per mostrare gli errori)
        this.ioconsole   = new IOConsole();

        //crea partita con inclusi labirinto, giocatore, borsa (il comando posa lavora su una partita)
        this.partita   = new Partita(ioconsole);

    }

    @Test
    public void testPosaUnAttrezzo() {
        //crea un posabile
        Attrezzo attrezzo  = new Attrezzo("attrezzo test", 5);
        this.partita.getGiocatore().addAttrezzo(attrezzo);

        //crea un comando di posatura
        Comando com_posa  = new ComandoPosa();
        com_posa.setIOConsole(this.ioconsole);
        com_posa.setParametro("attrezzo test");

        com_posa.esegui(partita);
        assertTrue(partita.getGiocatore().getStanzaCorrente().hasAttrezzo("attrezzo test"), "non sono riuscito a posare l'attrezzo nella stanza");
        
    }
}
