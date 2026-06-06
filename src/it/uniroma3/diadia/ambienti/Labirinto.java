package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Labirinto {
    private Stanza stanzaIniziale;
    private Stanza ultimaStanzaVincenteAggiunta;

    //nessuno può chiamare new Labirinto()
    //eccetto LabirintoBuilder
    private Labirinto() {}

    public void setStanzaIniziale(Stanza stanza) {
        this.stanzaIniziale = stanza;
    }

    public Stanza getStanzaIniziale() {
        return this.stanzaIniziale;
    }

    public void setStanzaVincente(Stanza stanza) {
        stanza.isVincente = true;
        ultimaStanzaVincenteAggiunta = stanza;
    }

    /*
    * metodo inutile/dannoso, introdotto per compatibilità con LabirintoBuilderTestCrescenzi
    */
    public Stanza getStanzaVincente() {
        return ultimaStanzaVincenteAggiunta;
    }

    public static class LabirintoBuilder {
        private Labirinto labirinto;
        private Map<String, Stanza> nome2stanza;
        private Stanza ultimaStanzaTrovata;
        private Stanza stanzaTest;

        public LabirintoBuilder() {
            labirinto = new Labirinto();
            nome2stanza = new HashMap<>();
        }

        public void addStanzaTest(String nomeStanza) {
            this.stanzaTest = trovaStanza(nomeStanza);
        }

        public Stanza getStanzaTest() {
            return this.stanzaTest;
        }

        private Stanza trovaStanza(String nomeStanza) {
            if(nomeStanza == null) {
                throw new RuntimeException("il nome stanza è obbligatorio, impossibile continuare");
            }
            Stanza stanza;
            if (nome2stanza.containsKey(nomeStanza)) {
                stanza = nome2stanza.get(nomeStanza);
            }
            else {
                stanza = new Stanza(nomeStanza);
                nome2stanza.put(stanza.getNome(), stanza);
            }
            ultimaStanzaTrovata = stanza;
            return stanza;
        }

        public LabirintoBuilder addStanza(String nomeStanza) {
            trovaStanza(nomeStanza);
            return this;
        }

        public LabirintoBuilder addStanzaMagica(String nomeStanzaMagica, int numeroPosature) {
            if (nome2stanza.containsKey(nomeStanzaMagica)) {
                throw new RuntimeException("non puoi creare una stanza magica con un nome già creato");
            }
            Stanza stanza = new StanzaMagica(nomeStanzaMagica, numeroPosature);
            ultimaStanzaTrovata = stanza;
            nome2stanza.put(stanza.getNome(), stanza);
            return this;
        }

        public LabirintoBuilder addStanzaBloccata(String nomeStanzaBloccata, String nomeDirezione, String nomeAttrezzoAntiblocco) {
            if (nome2stanza.containsKey(nomeStanzaBloccata)) {
                throw new RuntimeException("non puoi creare una stanza bloccata con un nome già creato");
            }
            Stanza stanza = new StanzaBloccata(nomeStanzaBloccata, nomeAttrezzoAntiblocco, Direzione.fromString(nomeDirezione));
            ultimaStanzaTrovata = stanza;
            nome2stanza.put(stanza.getNome(), stanza);
            return this;
        }

        public LabirintoBuilder addStanzaBuia(String nomeStanzaBuia, String nomeAttrezzoAntibuio) {
            if (nome2stanza.containsKey(nomeStanzaBuia)) {
                throw new RuntimeException("non puoi creare una stanza buia con un nome già creato");
            }
            Stanza stanza = new StanzaBuia(nomeStanzaBuia, nomeAttrezzoAntibuio);
            ultimaStanzaTrovata = stanza;
            nome2stanza.put(stanza.getNome(), stanza);
            return this;
        }

        public LabirintoBuilder addStanzaIniziale(String nomeStanza) {
            Stanza stanza = trovaStanza(nomeStanza);
            labirinto.setStanzaIniziale(stanza);
            return this;
        }

        public LabirintoBuilder addStanzaVincente(String nomeStanza) {
            Stanza stanza = trovaStanza(nomeStanza);
            labirinto.setStanzaVincente(stanza);
            return this;
        }

        public LabirintoBuilder addAdiacenza(String nomeStanzaDa, String nomeStanzaA, Direzione direzione) {
            Stanza stanzaDa = trovaStanza(nomeStanzaDa);
            Stanza stanzaA = trovaStanza(nomeStanzaA);
            stanzaDa.impostaStanzaAdiacente(direzione, stanzaA);
            return this;
        }
        /*
        * overload per supportare la classe selvaggia LabirintoBuilderTestCrescenzi
        */
        public LabirintoBuilder addAdiacenza(String nomeStanzaDa, String nomeStanzaA, String nomeDirezione) {
            return addAdiacenza(nomeStanzaDa, nomeStanzaA, Direzione.fromString(nomeDirezione));
        }

        public LabirintoBuilder addAttrezzo(String nomeStanza, String nomeAttrezzo, int peso) {
            Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
            Stanza stanza = trovaStanza(nomeStanza);
            stanza.addAttrezzo(attrezzo);
            return this;
        }

        public Labirinto getLabirinto() {
            if (labirinto.getStanzaIniziale()==null) {
                throw new RuntimeException("non puoi ottenere un labirinto senza una stanza iniziale");
            }
            return this.labirinto;
        }
        
        public Map<String, Stanza> getListaStanze() {
            //lista?!?
            return nome2stanza;
        }

        /*
        * metodi che ricordano l'ultima stanza, rompono la prevedibilità della funzione
        */
        public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso) {
            Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
            Stanza stanza = ultimaStanzaTrovata;
            if (stanza == null) {
                throw new IllegalArgumentException("nessuna stanza di riferimento");
            }
            stanza.addAttrezzo(attrezzo);
            return this;
        }

        /**
         * Crea tutte le stanze vers Base e le porte di collegamento
         */
        public LabirintoBuilder creaLabirintoBase() {
            
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
            labIA  .impostaStanzaAdiacente(Direzione.EST,   magaz  );
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
            labirinto.setStanzaIniziale(atrio);

            // il gioco finisce nella biblioteca
            labirinto.setStanzaVincente(bibliot);

            return this;

        }//end creaLabirintoBase

    }//end LabirintoBuilder

}//end Labirinto
