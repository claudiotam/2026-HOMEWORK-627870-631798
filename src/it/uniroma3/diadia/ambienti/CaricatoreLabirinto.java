package it.uniroma3.diadia.ambienti;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class CaricatoreLabirinto {

    /* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
    private static final String STANZE_MARKER = "Stanze:";             

    /* prefisso di una singola riga contenente il nome della stanza iniziale */
    private static final String STANZA_INIZIALE_MARKER = "Inizio:";    

    /* prefisso della riga contenente il nome stanza vincente */
    private static final String STANZA_VINCENTE_MARKER = "Vincente:";  

    /* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
    private static final String ATTREZZI_MARKER = "Attrezzi:";

    /* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
    private static final String USCITE_MARKER = "Uscite:";

    /*
     *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

        Stanze: biblioteca, N10, N11
        Inizio: N10
        Vincente: N11
        Attrezzi: martello 10 biblioteca, pinza 2 N10
        Uscite: biblioteca nord N10, biblioteca sud N11

     */
    private LineNumberReader reader;

    private Map<String, Stanza> nome2stanza;

    private Stanza stanzaIniziale;
    private Stanza ultimaStanzaVincenteAggiunta;


    public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
        this.nome2stanza = new HashMap<String,Stanza>();
        this.reader = new LineNumberReader(new FileReader(nomeFile));
    }

    public void carica() throws FormatoFileNonValidoException {
        try {
            this.leggiECreaStanze();
            this.leggiInizialeEvincente();
            this.leggiECollocaAttrezzi();
            this.leggiEImpostaUscite();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

    }

    private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
        try {
            String riga = this.reader.readLine();
            check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
            return riga.substring(marker.length());
        } catch (IOException e) {
            throw new FormatoFileNonValidoException(e.getMessage());
        }
    }

    private void leggiECreaStanze() throws FormatoFileNonValidoException  {
        String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
        for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
            Stanza stanza = new Stanza(nomeStanza);
            this.nome2stanza.put(nomeStanza, stanza);
        }
    }

    private List<String> separaStringheAlleVirgole(String str) {
        List<String> result = new LinkedList<>();
        Scanner scanner = new Scanner(str);
        scanner.useDelimiter(",");
        try (Scanner scannerDiParole = scanner) {
            result.add(scannerDiParole.next());
        }
        return result;
    }

    private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
        String nomeStanzaIniziale = null;
        nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
        check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
        String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
        check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
        this.stanzaIniziale = this.nome2stanza.get(nomeStanzaIniziale);
        impostaVincente(nomeStanzaVincente);
    }

    private void impostaVincente(String nomeStanzaVincente) throws FormatoFileNonValidoException {
        check(isStanzaValida(nomeStanzaVincente),"Stanza vincente sconosciuta ");
        Stanza stanzaVincente = this.nome2stanza.get(nomeStanzaVincente);
        ultimaStanzaVincenteAggiunta = stanzaVincente;
        stanzaVincente.isVincente = true;
    }

    private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
        String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

        for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
            String nomeAttrezzo = null;
            String pesoAttrezzo = null;
            String nomeStanza = null; 
            try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
                check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
                nomeAttrezzo = scannerLinea.next();
                check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
                pesoAttrezzo = scannerLinea.next();
                check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
                nomeStanza = scannerLinea.next();
            }                
            posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
        }
    }

    private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
        int peso;
        try {
            peso = Integer.parseInt(pesoAttrezzo);
            Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
            check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
            this.nome2stanza.get(nomeStanza).addAttrezzo(attrezzo);
        }
        catch (NumberFormatException e) {
            check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
        }
    }


    private boolean isStanzaValida(String nomeStanza) {
        return this.nome2stanza.containsKey(nomeStanza);
    }

    private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
        String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
        try (Scanner scannerDiLinea = new Scanner(specificheUscite)) {            

            while (scannerDiLinea.hasNext()) {
                check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
                String stanzaPartenza = scannerDiLinea.next();
                check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
                String dir = scannerDiLinea.next();
                check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
                String stanzaDestinazione = scannerDiLinea.next();
                
                impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
            }
        } 
    }
    
    private String msgTerminazionePrecoce(String msg) {
        return "Terminazione precoce del file prima di leggere "+msg;
    }

    private void impostaUscita(String nomeStanzaDa, String nomeDir, String nomeStanzaA) throws FormatoFileNonValidoException {
        check(isStanzaValida(nomeStanzaDa),"Stanza di partenza sconosciuta "+nomeDir);
        check(isStanzaValida(nomeStanzaA),"Stanza di destinazione sconosciuta "+ nomeDir);
        Stanza partenzaDa = this.nome2stanza.get(nomeStanzaDa);
        Stanza arrivoA = this.nome2stanza.get(nomeStanzaA);
        partenzaDa.impostaStanzaAdiacente(nomeDir, arrivoA);
    }

    final private void check(boolean condizioneCheDeveEssereVera, String messaggioErrore) throws FormatoFileNonValidoException {
        if (!condizioneCheDeveEssereVera)
            throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);
    }

    public Stanza getStanzaIniziale() {
        return this.stanzaIniziale;
    }

    public Stanza getUltimaStanzaVincenteAggiunta() {
        return this.ultimaStanzaVincenteAggiunta;
    }
}