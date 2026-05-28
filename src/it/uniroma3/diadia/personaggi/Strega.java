package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends Personaggio {
    private static final String NOME_DEFAULT = "Lastrega";    
    private static final String PRESENTAZIONE_DEFAULT = "La mia qualifica è: Strega. Grazie e Arrivederci. ";

    public Strega(IO ioconsole, String nome, String presentazione) {
        super(ioconsole, nome, presentazione);
    }

    public Strega(IO ioconsole) {
        this(ioconsole, NOME_DEFAULT, PRESENTAZIONE_DEFAULT);
    }

    @Override
    public void riceviRegalo(Partita partita, Attrezzo attrezzo) {
        ioconsole.mostraMessaggio("Grazie! Questo/a " + attrezzo.getNome() + " mi piace e me lo tengo. Rido! AHAHAH!. ");
    }
}