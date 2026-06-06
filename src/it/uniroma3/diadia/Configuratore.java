package it.uniroma3.diadia;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuratore {

    private static final String DIADIA_PROPERTIES = "diadia.properties";
    private static final String CFU = "cfu";
    private static final String PESO_MAX = "pesoMax";

    private Properties prop;

    public Configuratore() {
        carica();
    }

public void carica() {
    try (FileInputStream input = new FileInputStream(DIADIA_PROPERTIES)) {
        Properties p = new Properties();
        p.load(input);
        this.prop = p;
    } catch (IOException e) {
        this.prop = null;
    }
}

    public Integer getCFU() {
        return getIntProperty(CFU);
    }

    public Integer getPesoMax() {
        return getIntProperty(PESO_MAX);
    }

    private Integer getIntProperty(String key) {
        if (prop == null) return null;

        String value = prop.getProperty(key);
        if (value == null) return null;

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}