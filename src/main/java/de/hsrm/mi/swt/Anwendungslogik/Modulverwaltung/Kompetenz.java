package de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung;

/**
 * Kompetenz, die man für ein Modul benötigt
 * @author Marie Bohnert, Beate Arnold, Arthur Fieguth
 */
public class Kompetenz {

    private String name;

    /**
     * Konstruktor für die Kompetenz
     * @param name : Name der Kompetenz
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
