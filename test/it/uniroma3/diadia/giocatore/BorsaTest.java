package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * 
 */
class BorsaTest {
    private Borsa borsa;
    private Attrezzo attrezzoLeggero;

    @BeforeEach
    void setUp() {
        // Creiamo un'istanza della borsa con peso massimo di 10
        borsa = new Borsa(10);

        // Creiamo alcuni attrezzi per testare la borsa
        attrezzoLeggero = new Attrezzo("Spada", 3); // peso 3kg
        new Attrezzo("Scudo", 8);    }

    @Test
    void LaBorsaIniziaVuota() {
        // La borsa dovrebbe essere vuota al momento della creazione
    	//verifica che la borsa iniziale è vuota
        assertTrue(borsa.isEmpty(), "la borsa iniziale dovrebbe essere vuota");
    }

    @Test
    void LaBorsaContieneCose() {
    	//vericifa che la borsa accetta ricevere un libro
        // Aggiungiamo un attrezzo e verifichiamo che la borsa non sia più vuota
        borsa.addAttrezzo(new Attrezzo("libro", 1));
        assertFalse(borsa.isEmpty(), "la borsa più un libro non dovrebbe essere vuota");
    }

    @Test
    void testPrendereAttrezzoBorsaPesoMassimoSingolo() {
        borsa.addAttrezzo(new Attrezzo("automobile", 3500));
        assertTrue(borsa.isEmpty(), "la borsa da 10 chili non dovrebbe accettare una automobile da 3500 chili");
    }
    
    @Test
    public void testGetAttrezzo() {
        // Aggiungiamo un attrezzo e verifichiamo che il metodo restituisca l'attrezzo corretto
        borsa.addAttrezzo(attrezzoLeggero);
        Attrezzo trovato = borsa.getAttrezzo("Spada");
        assertNotNull(trovato);
        assertEquals("Spada", trovato.getNome());
        assertEquals(3, trovato.getPeso());
        
        // Verifichiamo che un attrezzo non presente nella borsa restituisca null
        assertNull(borsa.getAttrezzo("Martello"));
    }

    @Test
    public void testHasAttrezzo() {
        // Verifica che la borsa non abbia un attrezzo prima di aggiungerlo
        assertFalse(borsa.hasAttrezzo("Spada"));

        // Aggiungiamo un attrezzo e verifichiamo che la borsa ora lo contenga
        borsa.addAttrezzo(attrezzoLeggero);
        assertTrue(borsa.hasAttrezzo("Spada"));
    }

    @Test
    public void testToString() {
        // Verifica il contenuto della borsa quando è vuota
        assertTrue(borsa.getDescrizione().contains("vuota"), "il metodo toString di borsa vuota dovrebbe contenere la parola 'vuota'");

        // Aggiungiamo un attrezzo e verifichiamo la rappresentazione della borsa
        borsa.addAttrezzo(attrezzoLeggero);
        assertTrue(borsa.getDescrizione().contains("Contenuto borsa"), "il metodo toString di borsa piena dovrebbe contenere la parola 'Contenuto borsa'");
        assertTrue(borsa.getDescrizione().contains("Spada"), "il metodo toString di borsa piena di spada dovrebbe contenere la parola 'Spada'");
    }
    
    @Test
    public void testPrendereAttrezzoBorsaPesiMassimiMultipli() {
        // Proviamo ad aggiungere più di 10 attrezzi (la borsa è limitata a 10 attrezzi)
        for (int i = 0; i < 10; i++) {
            Attrezzo att = new Attrezzo("attrezzo" + i, 1);
            borsa.addAttrezzo(att);
        }

        // Proviamo ad aggiungere un altro attrezzo, ma non dovrebbe essere possibile
        Attrezzo attrezzoInEccesso = new Attrezzo("attrezzoInEccesso", 1);
        assertFalse(borsa.addAttrezzo(attrezzoInEccesso), "Non dovrebbe essere possibile prendere più di 10 attrezzi");
    }

    @Nested
    class TestOrdinamenti {
        Attrezzo atA, atB, atC, atD;

        @BeforeEach
        void setUp() {
            // Aggiungiamo 5 attrezzi
            atA = new Attrezzo("atA", 3);
            atB = new Attrezzo("atB", 1);
            atC = new Attrezzo("atC", 1);
            atD = new Attrezzo("atD", 3);
            
            borsa.addAttrezzo(atA);
            borsa.addAttrezzo(atC);
            borsa.addAttrezzo(atD);
            borsa.addAttrezzo(atB);
        }

        @Test
        public void testGetContenutoOrdinatoPerPeso() {
            List<Attrezzo> result = borsa.getContenutoOrdinatoPerPeso();
            List<Attrezzo> expected = List.of(atB, atC, atA, atD);

            assertEquals(expected, result, "Ordinamento errato");
        }

        @Test
        public void testGetContenutoOrdinatoPerNome() {
            SortedSet<Attrezzo> actual = borsa.getContenutoOrdinatoPerNome();
            List<Attrezzo> actual_listOf = new ArrayList<>(actual);

            List<Attrezzo> expected = List.of(atA, atB, atC, atD);

            assertEquals(expected, actual_listOf);
        }

        @Test
        public void testGetContenutoRaggruppatoPerPeso() {
            Map<Integer, Set<Attrezzo>> actual = borsa.getContenutoRaggruppatoPerPeso();
            
            Map<Integer, Set<Attrezzo>> expected = Map.of(
                1, Set.of(atB, atC),
                3, Set.of(atA, atD)
            );

            assertEquals(expected, actual);
        }

        @Test
        public void testGetSortedSetOrdinatoPerPeso() {
            SortedSet<Attrezzo> actual = borsa.getSortedSetOrdinatoPerPeso();
            List<Attrezzo> actual_listOf = new ArrayList<>(actual);

            List<Attrezzo> expected = List.of(atB, atC, atA, atD);

            assertEquals(expected, actual_listOf);
        }
    }
}
