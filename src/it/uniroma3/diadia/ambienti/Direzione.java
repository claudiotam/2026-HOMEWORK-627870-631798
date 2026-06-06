package it.uniroma3.diadia.ambienti;

public enum Direzione {
    NORD,
    SUD,
    EST,
    OVEST;

    public static boolean isDirezione(String nomeDirezione) {
        if (nomeDirezione == null) return false;
        for (Direzione d : Direzione.values()) {
            if (d.name().equalsIgnoreCase(nomeDirezione)) return true;
        }
        return false;
    }

    public static Direzione fromString(String nomeDirezione) {
        if (nomeDirezione == null) return null;
        for (Direzione direzione : Direzione.values()) {
            if (direzione.name().equalsIgnoreCase(nomeDirezione)) return direzione;
        }
        return null;
    }
}
