package it.uniroma3.diadia;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class Configuratore {

    private static final String DIADIA_PROPERTIES = "diadia.properties";
    private static final String PESO_MAX = "pesoMax";
    private static final String CFU = "cfu";
    private Properties prop;
    
    public Configuratore() {
    }

    public Integer getCFU() {
        if (prop.getProperty(CFU)==null) return null;
        return Integer.parseInt(prop.getProperty(CFU));
    }
    
    public Integer getPesoMax() {
        if (prop.getProperty(PESO_MAX)==null) return null;
        return Integer.parseInt(prop.getProperty(PESO_MAX));
    }

    public boolean carica() {
        prop = new Properties();
        try {
            FileInputStream input = new FileInputStream(DIADIA_PROPERTIES);
            prop.load(input);
            return true;
        } catch (IOException e) {
            //e.printStackTrace();
            //throw new RuntimeException(e);
        }
        return false;
    }
}