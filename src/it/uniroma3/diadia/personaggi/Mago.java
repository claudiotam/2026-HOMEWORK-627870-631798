package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends Personaggio {
    private static final String NOME_DEFAULT = "Ilmago";    
    private static final String PRESENTAZIONE_DEFAULT = "La mia qualifica è: Mago. Grazie e Arrivederci. ";

    public Mago(IO ioconsole, String nome, String presentazione) {
        super(ioconsole, nome, presentazione);
    }

    public Mago(IO ioconsole) {
        this(ioconsole, NOME_DEFAULT, PRESENTAZIONE_DEFAULT);
    }

    @Override
    public void riceviRegalo(Partita partita, Attrezzo attrezzo) {
        ioconsole.mostraMessaggio("Grazie! Dimezzo il prezzo all'oggetto chiamato " + attrezzo.getNome() +" e lo rilascio. ");
        attrezzo.dimezzaPeso();
        Stanza stanzaCorrente = partita.getGiocatore().getStanzaCorrente();
        if (stanzaCorrente.acceptsAttrezzo(attrezzo)) {
            stanzaCorrente.addAttrezzo(attrezzo);
        }
    }
}