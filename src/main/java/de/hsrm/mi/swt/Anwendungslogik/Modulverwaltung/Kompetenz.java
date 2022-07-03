package de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung;

public class Kompetenz {

    private String name;

    /**
     * Konstruktor für die Kompetenz
     * @param name
     */
    public Kompetenz(String name) {
        this.name = name;
    }
    
    /** 
     * Gibt den Namen von der Kompetenz zurück
     * @return String
     */
    public String getName() {
        return name;
    }    
}
