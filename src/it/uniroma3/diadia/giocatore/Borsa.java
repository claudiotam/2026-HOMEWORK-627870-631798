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

import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
    public final static int DEFAULT_PESO_MAX_BORSA = 10;
    private Map<String, Attrezzo> attrezzi;
    private int pesoMax;

    public Borsa() {
        this(DEFAULT_PESO_MAX_BORSA);
    }

    public Borsa(int pesoMax) {
        this.pesoMax = pesoMax;
        this.attrezzi = new HashMap<>();
    }

    public boolean acceptsAttrezzo(Attrezzo attrezzo) {
    	if (attrezzo == null) return false;
        if (this.getPeso() + attrezzo.getPeso() > this.pesoMax)
            return false;
        return true;
    }

    public boolean addAttrezzo(Attrezzo attrezzo) {
    	if (!acceptsAttrezzo(attrezzo)) return false;
        attrezzi.put(attrezzo.getNome(), attrezzo);
        return true;
    }

    public boolean hasAttrezzo(String nomeAttrezzo) {
        return attrezzi.containsKey(nomeAttrezzo);
    }

    public Attrezzo getAttrezzo(String nomeAttrezzo) {
        return attrezzi.getOrDefault(nomeAttrezzo, null);
    }

    public void removeAttrezzo(String nomeAttrezzo) {
        attrezzi.remove(nomeAttrezzo);
    }

    public boolean isEmpty() {
        return attrezzi.isEmpty();
    }

    public int getPeso() {
        int peso = 0;
        for (Attrezzo at : attrezzi.values())
            peso += at.getPeso();
        return peso;
    }

    @Override
    public String toString() {
        return "<Oggetto borsa>";
    }

    public String getDescrizione() {
        StringBuilder s = new StringBuilder();

        if (!this.isEmpty()) {
            s.append("Contenuto borsa (" + this.getPeso() + "kg/" + this.pesoMax + "kg): ");
            for (Attrezzo at : attrezzi.values())
                s.append(at.toString() + " ");
        } else
            s.append("Borsa vuota");
        return s.toString();
    }

    /*
    ESERCIZIO HW3.3:
    definizione vari comparatori
    */
    private static class ComparatoreNomi implements Comparator<Attrezzo> {
        @Override
        public int compare(Attrezzo a, Attrezzo b) {
            return (a.getNome().compareTo(b.getNome()));
        }
    }
    private static class ComparatorePesi implements Comparator<Attrezzo> {
        @Override
        public int compare(Attrezzo a, Attrezzo b) {
            return Integer.compare(a.getPeso(), b.getPeso());
        }
    }
    private static class ComparatoreNomiPoiPesi implements Comparator<Attrezzo> {
        @Override
        public int compare(Attrezzo a, Attrezzo b) {
            int cNomi = (new ComparatoreNomi()).compare(a,b);
            if (cNomi != 0) return cNomi;
            return (new ComparatorePesi()).compare(a,b);
        }
    }
    private static class ComparatorePesiPoiNomi implements Comparator<Attrezzo> {
        @Override
        public int compare(Attrezzo a, Attrezzo b) {
            int cPesi = (new ComparatorePesi()).compare(a,b);
            if (cPesi != 0) return cPesi;
            return (new ComparatoreNomi()).compare(a,b);
        }
    }

    /*
    ESERCIZIO HW3.3:
    restituisce la lista degli attrezzi nella borsa ordinati per peso e quindi,
    a parità di peso, per nome
    */
    List<Attrezzo> getContenutoOrdinatoPerPeso() {
        List<Attrezzo> r = new ArrayList<>(attrezzi.values());
        r.sort(new ComparatorePesiPoiNomi());
        return r;
    }

    /*
    ESERCIZIO HW3.3:
    restituisce l'insieme degli attrezzi nella borsa ordinati per nome
    */
    SortedSet<Attrezzo> getContenutoOrdinatoPerNome() {
        TreeSet<Attrezzo> r = new TreeSet<>(new ComparatoreNomiPoiPesi());
        r.addAll(attrezzi.values());
        return r;
    }
    
    /*
    ESERCIZIO HW3.3:
    restituisce una mappa che associa un intero (rappresentante un
    peso) con l’insieme (comunque non vuoto) degli attrezzi di tale peso:
    tutti gli attrezzi dell'insieme che figura come valore hanno lo stesso
    peso pari all'intero che figura come chiave
    */
    Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
        HashMap<Integer, Set<Attrezzo>> r = new HashMap<>();
        for (Attrezzo at : attrezzi.values()) {
             int peso = at.getPeso();
            if (!r.containsKey(peso)) {
                r.put(peso, new HashSet<Attrezzo>());
            }
            r.get(peso).add(at);
        }
        return r;
    }

    /*
    ESERCIZIO HW3.4:
    restituisce l'insieme gli attrezzi nella borsa ordinati
    per peso e quindi, a parità di peso, per nome
     */
    SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
        TreeSet<Attrezzo> r = new TreeSet<>(new ComparatorePesiPoiNomi());
        r.addAll(attrezzi.values());
        return r;
    }
}

