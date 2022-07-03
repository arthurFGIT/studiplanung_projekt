package de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung;
/**
 * Das Fachsemester ist definiert durch eine Nummer z.B. 1. Semester und ob es ein Winter- oder Sommersemester ist
 * @author Marie Bohnert, Beate Arnold, Arthur Fieguth
 */
public class Fachsemester {

    private int id;
    private AngebotsIntervall angebotsIntervall;

    /**
     * Konstruktor für das Fachsemester
     * @param id : Semesterzahl
     * @param angebotsIntervall : Winter- oder Sommersemester
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
