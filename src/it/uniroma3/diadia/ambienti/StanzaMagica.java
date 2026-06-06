package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/*
 * Una stanza magica ha delle particolarità, che la
* rendono diversa dalla stanza ordinaria:
* – dopo N volte che in tale stanza viene posato (aggiunto) un qualsiasi attrezzo da parte del giocatore, la stanza inizierà a comportarsi «magicamente» in modo irreversibile
* – comportamento magico: la stanza "inverte" il nome di ogni attrezzo posato (chiave->evaihc) e ne raddoppia il peso. 
* - 
*/
public class StanzaMagica extends Stanza {
    final static private int POSATURE_RESIDUE_DEFAULT = 3;
    //teniamo traccia solo delle posature residue prima di attivare la magia
    private int posatureResidue;

    public StanzaMagica(String nome) {
        this(nome, POSATURE_RESIDUE_DEFAULT);
    }

    public StanzaMagica(String nome, int posatureResidue) {
        super(nome);
        this.posatureResidue = posatureResidue;
    }

    @Override
    public boolean addAttrezzo(Attrezzo attrezzo) {
        if (posatureResidue > 0) {
            posatureResidue --;
        }
        else {
            //la modifica del nome rende inutilizzabili tutte le hashmap e hashset che contengono l'attrezzo
            attrezzo.invertiNomeAumentaPeso();
        }
        //nota, la stanza ha vincoli di capienza di peso/quantita/altro; prima di inserire devo testare i vincoli
        if (!acceptsAttrezzo(attrezzo)) return false;
        else return super.addAttrezzo(attrezzo);
    }

    /*
    funzione brutta copia di instanceof, richiesta per compatibilità con LabirintoBuilderTestCrescenzi
    */
    public boolean isMagica() {
        return true;
    }
}
