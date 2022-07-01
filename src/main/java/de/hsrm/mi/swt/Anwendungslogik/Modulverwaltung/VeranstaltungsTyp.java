package de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung;

public enum VeranstaltungsTyp {
    PRAKTIKUM("PRAKTIKUM"), VORLESUNG("VORLESUNG"), UEBUNG("UEBUNG"), PORTFOLIO("PORTFOLIO"), LISTENFACH("LISTENFACH");

    public String name;
    
    private VeranstaltungsTyp(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
