package it.uniroma3.diadia.ambienti;

/*
 * La «stanza bloccata»: una delle direzioni della
 * stanza non può essere seguita a meno che nella
 * stanza non sia presente un oggetto con un nome
 * particolare (ad esempio "passepartout" o "piedediporco")
 */
public class StanzaBloccata extends Stanza {
    final static private String    nomeAttrezzoAntiBlocco_DEFAULT = "passepartout";
    final static private Direzione DIREZIONE_BLOCCATA_DEFAULT        = Direzione.EST;
    private String nomeAttrezzoAntiBlocco;
    private Direzione direzione_bloccata;

    public StanzaBloccata(String nome) {
        this(nome, nomeAttrezzoAntiBlocco_DEFAULT, DIREZIONE_BLOCCATA_DEFAULT);
    }

    public StanzaBloccata(String nome, String nomeAttrezzoAntiBlocco, Direzione direzione_bloccata) {
        super(nome);
        this.nomeAttrezzoAntiBlocco = nomeAttrezzoAntiBlocco;
        this.direzione_bloccata = direzione_bloccata;
    }

    /*
    * overload retrocompatibilità per far funzionare LabirintoBuilderTestCrescenzi
    */
    public StanzaBloccata(String nome, String nomeDirezioneBloccata, String nomeAttrezzoAntiBlocco) {
        this(nome, nomeAttrezzoAntiBlocco, Direzione.valueOf(nomeDirezioneBloccata.toUpperCase()));
    }

    @Override
    public Stanza getStanzaAdiacente(Direzione direzione) {
        if (!direzione.equals(this.direzione_bloccata) || this.hasAttrezzo(nomeAttrezzoAntiBlocco)) {
            return super.getStanzaAdiacente(direzione);
        }
        else {
            return this;
        }
	}

    @Override
    public String getDescrizione() {
        return super.getDescrizione() + "\n" + 
        "Questa stanza potrebbe avere qualche direzione bloccata, ma sbloccabile da qualche attrezzo (forse il passepartout?)";
    }
}
