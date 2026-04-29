/**
 * Questa classe disaccoppia l'IO
 *
 * @author Claudio Tam
 * @see 
 * @version base
 */

package it.uniroma3.diadia;

import java.util.Scanner;

public class IOConsole implements IO {
    Scanner scannerDiLinee;
    public IOConsole() {
        scannerDiLinee = new Scanner(System.in);
    }
    public void mostraMessaggio(String msg) {
        System.out.println(msg);
    }
    public String leggiRiga() {
        String riga = scannerDiLinee.nextLine();
        return riga;
    }
}