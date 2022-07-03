package de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung;
/**
 * Angebotsintervall Enum, Winter oder Sommersemester oder sowohl als auch
 * @author Marie Bohnert, Beate Arnold, Arthur Fieguth
 */
public enum AngebotsIntervall {
    WINTER("WINTER"), SOMMER("SOMMER"), WISO("WISO");
    public String name;
    
    /**
     * Angebotsintervall Konstruktor
     * @param name : Name vom Angebotsintervall
     */
    private AngebotsIntervall(String name){
        this.name = name;
    }

    /**
     * Gibt Namen vom Angebotsintervall-Enum zurück
     * @return name
     */
    public String getName(){
        return name;
    }
}

