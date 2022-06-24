package de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung;

public enum AngebotsIntervall {
    WINTER("WINTER"), SOMMER("SOMMER"), WISO("SOMMER");
    public String name;
    
    private AngebotsIntervall(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}

