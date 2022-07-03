package de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung;

public class Fachsemester {

    private int id;
    private AngebotsIntervall angebotsIntervall;

    /**
     * Konstruktor für das Fachsemester
     * @param id
     * @param angebotsIntervall
     */
    public Fachsemester(int id, AngebotsIntervall angebotsIntervall) {
        this.id = id;
        this.angebotsIntervall = angebotsIntervall;
    }


    
    /** 
     * Gibt ID vom Fachsemester zurück (id = semesterzahl)
     * @return int
     */
    public int getid() {
        return id;
    }
        
    /** 
     * Gibt das AngebotsIntervall als Enum zurück
     * @return AngebotsIntervall
     */
    public AngebotsIntervall getAngebotsIntervall() {
        return angebotsIntervall;
    }
    
    /** 
     * Setzt das Angebotsintervall
     * @param angebotsIntervall
     */
    public void setAngebotsIntervall(AngebotsIntervall angebotsIntervall) {
        this.angebotsIntervall = angebotsIntervall;
    }

    
    
}
