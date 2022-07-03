package de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung;

/**
 * VeranstaltungsTyp Enum, der alle VeranstaltungsTypen mit String enthält
 * @author Marie Bohnert, Beate Arnold, Arthur Fieguth
 */
public enum VeranstaltungsTyp {
    PRAKTIKUM("PRAKTIKUM"), VORLESUNG("VORLESUNG"), UEBUNG("UEBUNG"), PORTFOLIO("PORTFOLIO"), LISTENFACH("LISTENFACH");

    public String name;
    
    /**
     * Konstruktur für den VeranstaltungsTypen
     * @param name : Name des typs
     */
    private VeranstaltungsTyp(String name){
        this.name = name;
    }

    /**
     * Gibt namen vom Typ zurück
     * @return name : VeranstaltungsTyp Name
     */
    public String getName(){
        return name;
    }
}
