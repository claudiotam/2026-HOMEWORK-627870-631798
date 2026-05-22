package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Labirinto {
    Stanza stanzaIniziale;
    Stanza stanzaVincente;

    public Labirinto() {
    }

    /**
     * Crea tutte le stanze vers Base e le porte di collegamento
     */
    public void creaLabirintoBase() {
        
        /* crea gli attrezzi */
        Attrezzo lanterna = new Attrezzo("lanterna",     9);
        Attrezzo osso     = new Attrezzo("osso",         2);
        Attrezzo ossino   = new Attrezzo("ossino",       1);
        Attrezzo armadio  = new Attrezzo("armadio",    500);
        Attrezzo bidone   = new Attrezzo("bidone",       9);
        Attrezzo chiave   = new Attrezzo("chiave",       4);
        Attrezzo ppt      = new Attrezzo("passepartout", 4);
        
        /* crea stanze del labirinto normali */
        Stanza bibliot = new Stanza("Biblioteca");

        Stanza laborat = new Stanza       ("Laboratorio Campus");
        Stanza atrio   = new Stanza       ("Atrio");
        Stanza aulaN11 = new Stanza       ("Aula N11");

        Stanza aulaN10 = new Stanza       ("Aula N10");
        
        /* crea stanze del labirinto speciali */
        Stanza labIA   = new StanzaMagica  ("LabIA");
        Stanza magaz   = new StanzaBuia    ("Magazzino");
        Stanza presi   = new StanzaBloccata("Presidenza");
        
        /* collega le stanze */
        laborat.impostaStanzaAdiacente(Direzione.NORD, null);
        laborat.impostaStanzaAdiacente(Direzione.SUD,   labIA  );
        laborat.impostaStanzaAdiacente(Direzione.EST,   atrio  );
        laborat.impostaStanzaAdiacente(Direzione.OVEST, aulaN11);
        
        atrio  .impostaStanzaAdiacente(Direzione.NORD,  bibliot);
        atrio  .impostaStanzaAdiacente(Direzione.SUD,   magaz  );
        atrio  .impostaStanzaAdiacente(Direzione.EST,   aulaN11);
        atrio  .impostaStanzaAdiacente(Direzione.OVEST, laborat);
        
        aulaN11.impostaStanzaAdiacente(Direzione.NORD, null);
        aulaN11.impostaStanzaAdiacente(Direzione.SUD,   presi  );
        aulaN11.impostaStanzaAdiacente(Direzione.EST,   laborat);
        aulaN11.impostaStanzaAdiacente(Direzione.OVEST, atrio  );
        
        // stanza magica
        labIA  .impostaStanzaAdiacente(Direzione.NORD,  laborat);
        labIA  .impostaStanzaAdiacente(Direzione.SUD,   aulaN10);
        labIA   .impostaStanzaAdiacente(Direzione.EST,  magaz  );
        labIA  .impostaStanzaAdiacente(Direzione.OVEST, presi  );
        
        // stanza buia
        magaz  .impostaStanzaAdiacente(Direzione.NORD,  atrio  );
        magaz  .impostaStanzaAdiacente(Direzione.SUD,   aulaN10);
        magaz  .impostaStanzaAdiacente(Direzione.EST,   presi  );
        magaz  .impostaStanzaAdiacente(Direzione.OVEST, labIA  );
        
        // stanza bloccata
        presi  .impostaStanzaAdiacente(Direzione.NORD,  aulaN11);
        presi  .impostaStanzaAdiacente(Direzione.SUD,   aulaN10);
        presi  .impostaStanzaAdiacente(Direzione.EST,   labIA  );
        presi  .impostaStanzaAdiacente(Direzione.OVEST, magaz  );
        
        aulaN10.impostaStanzaAdiacente(Direzione.NORD,  magaz  );
        aulaN10.impostaStanzaAdiacente(Direzione.SUD,   null);
        aulaN10.impostaStanzaAdiacente(Direzione.EST,   presi  );
        aulaN10.impostaStanzaAdiacente(Direzione.OVEST, labIA  );
        
        /*
         * schema di collegamento
         *                bibli
         *                  |
         *  <- laborat -  atrio  -  N11  ->
         *        |         |        |
         *  <-  labIA  -  magaz  - presi ->
         *        |         |        |
         *        \    -   N10   -   /
         */
        
        /* pone gli attrezzi nelle stanze */
        aulaN11.addAttrezzo(bidone);
        aulaN11.addAttrezzo(armadio);

        atrio  .addAttrezzo(lanterna);
        atrio  .addAttrezzo(osso);
        atrio  .addAttrezzo(ossino);
        atrio  .addAttrezzo(ppt);

        labIA  .addAttrezzo(chiave);

        // il gioco comincia nell'atrio
        this.stanzaIniziale = atrio;

        // il gioco finisce nella biblioteca
        this.stanzaVincente = bibliot;
    }

    public void setStanzaVincente(Stanza s) {
        this.stanzaVincente = s;
    }

    public void setStanzaIniziale(Stanza s) {
        this.stanzaIniziale = s;
    }

    public Stanza getStanzaVincente() {
        return this.stanzaVincente;
    }

    public Stanza getStanzaIniziale() {
        return this.stanzaIniziale;
    }

}
