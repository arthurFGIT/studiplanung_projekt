package de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung;

public enum AngebotsIntervall {
    WINTER("WINTER"), SOMMER("SOMMER"), WISO("SOMMER");
    public String name;
    
    /**
     * Angebotsintervall Konstruktor
     * @param name 
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

