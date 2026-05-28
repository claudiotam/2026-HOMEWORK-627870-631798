package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends Personaggio {
    private static final String NOME_DEFAULT = "Ilcane";    
    private static final String PRESENTAZIONE_DEFAULT = "La mia qualifica è: Cane. Grazie e Arrivederci. ";
    
    private String nome_attrezzo_gradito;
    private boolean ti_ho_gia_morso;

    public Cane(IO ioconsole, String nome, String presentazione, String nome_attrezzo_gradito) {
        super(ioconsole, nome, presentazione);
        this.ti_ho_gia_morso = false;
        this.nome_attrezzo_gradito = nome_attrezzo_gradito;
    }

    public Cane(IO ioconsole) {
        this(ioconsole, NOME_DEFAULT, PRESENTAZIONE_DEFAULT, "cibo_per_cani");
    }

    @Override
    public void riceviRegalo(Partita partita, Attrezzo attrezzo) {
        if (attrezzo.getNome().equals(nome_attrezzo_gradito)) {
            ioconsole.mostraMessaggio("Grazie! Il cibo mi piace. Ti regalo un CFU. ");
            partita.getGiocatore().mettiUnCfu();
        }
        else {
            ioconsole.mostraMessaggio("Questo cibo fa schifo!");

            if (!ti_ho_gia_morso) {
                ioconsole.mostraMessaggio("Ti mordo! Hai perso un CFU. ");
                partita.getGiocatore().togliUnCfu();
                ti_ho_gia_morso = true;
            }

            Stanza stanzaCorrente = partita.getGiocatore().getStanzaCorrente();
            if (stanzaCorrente.acceptsAttrezzo(attrezzo)) {
                stanzaCorrente.addAttrezzo(attrezzo);
            }
        }

    }
}