package it.uniroma3.diadia;

import java.util.LinkedList;
import java.util.List;

public class IOSimulator implements IO {
    // in ordine temporale, collezione di messaggi in viaggio dall'utente al gioco
    private List<String> messaggiIn;
    private int messaggiInReadCursor;

    // in ordine temporale, collezione di messaggi in viaggio dal gioco all'utente
    private List<String> messaggiOut;

    public IOSimulator(List<String> messaggi_in) {
        this.messaggiOut = new LinkedList<>();
        this.messaggiIn = messaggi_in;
        messaggiInReadCursor = 0;
    }

    public void mostraMessaggio(String msg) {
        // System.out.println(msg);
        messaggiOut.add(msg);
        System.out.println("messaggio out " + (messaggiOut.size()-1) + " scritto");
    }

    public String leggiRiga() {
        if (messaggiInReadCursor < messaggiIn.size()) {
            System.out.println("messaggio in " + messaggiInReadCursor + " letto");
            return messaggiIn.get(messaggiInReadCursor++);
        }
        else {
            System.out.println("messaggi in esauriti, invio stringa vuota");
            return "";
        }
    }

    public void stampaRegistroMessaggiIn() {
        int cur = 0;
        System.out.println("messaggi in già spediti:");
        while (cur < messaggiInReadCursor) {
            System.out.println(messaggiIn.get(cur++));
        }

        System.out.println("messaggi in ancora non spediti:");
        while (cur < messaggiIn.size()) {
            System.out.println(messaggiIn.get(cur++));
        }
    }

    public void stampaRegistroMessaggiOut() {
        System.out.println("messaggi out ricevuti:");
        for (String m : messaggiOut) {
            System.out.println(m);
        }
    }

    public List<String> getMessaggiOut() {
        return messaggiOut;
    }

}
