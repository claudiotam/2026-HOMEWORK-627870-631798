/**
 * Modella la borsa del personaggio
 * con gli attrezzi
 * 
 * 
 *
 * @author Claudio Tam
 * @see 
 * @version base
 */

package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
    public final static int DEFAULT_PESO_MAX_BORSA = 10;
    private Attrezzo[] attrezzi;
    private int numeroAttrezzi;
    private int pesoMax;

    public Borsa() {
        this(DEFAULT_PESO_MAX_BORSA);
    }

    public Borsa(int pesoMax) {
        this.pesoMax = pesoMax;
        this.attrezzi = new Attrezzo[10]; // speriamo bastino...
        this.numeroAttrezzi = 0;
    }

    public boolean acceptsAttrezzo(Attrezzo attrezzo) {
        if (this.getPeso() + attrezzo.getPeso() > this.pesoMax)
            return false;
        if (this.numeroAttrezzi == 10)
            return false;
        return true;
    }

    public boolean addAttrezzo(Attrezzo attrezzo) {
        if (this.getPeso() + attrezzo.getPeso() > this.pesoMax)
            return false;
        if (this.numeroAttrezzi == 10)
            return false;
        this.attrezzi[this.numeroAttrezzi] = attrezzo;
        this.numeroAttrezzi++;
        return true;
    }

    public Attrezzo getAttrezzo(String nomeAttrezzo) {
        for (int i = 0; i < this.numeroAttrezzi; i++) {
            if (this.attrezzi[i].getNome().equals(nomeAttrezzo)) {
                return attrezzi[i];
            }
        }
        return null;
    }

    public boolean hasAttrezzo(String nomeAttrezzo) {
        return this.getAttrezzo(nomeAttrezzo) != null;
    }

    public int getPeso() {
        int peso = 0;
        for (int i = 0; i < this.numeroAttrezzi; i++)
            peso += this.attrezzi[i].getPeso();

        return peso;
    }

    public boolean isEmpty() {
        return this.numeroAttrezzi == 0;
    }

    public boolean removeAttrezzo(String nomeAttrezzo) {
        int w = 0;
        int r = 0;
        for (r = 0; r < this.numeroAttrezzi; r++) {
            if (!this.attrezzi[r].getNome().equals(nomeAttrezzo)) {
                if (r != w) this.attrezzi[w] = this.attrezzi[r];
                w++;
            }
        }
        if (r == w) return false;
        else {
            this.numeroAttrezzi = w;
            return true;
        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder();

        if (!this.isEmpty()) {
            s.append("Contenuto borsa (" + this.getPeso() + "kg/" + this.pesoMax + "kg): ");
            for (int i = 0; i < this.numeroAttrezzi; i++)
                s.append(attrezzi[i].toString() + " ");
        } else
            s.append("Borsa vuota");
        return s.toString();
    }
}