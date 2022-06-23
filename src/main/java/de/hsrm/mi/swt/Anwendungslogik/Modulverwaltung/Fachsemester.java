package de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung;

public class Fachsemester {

    private int id;
    private AngebotsIntervall angebotsIntervall;

    public Fachsemester(int id, AngebotsIntervall angebotsIntervall) {
        this.id = id;
        this.angebotsIntervall = angebotsIntervall;
    }


    public int getid() {
        return id;
    }
    public void setid(int id) {
        this.id = id;
    }
    public AngebotsIntervall getAngebotsIntervall() {
        return angebotsIntervall;
    }
    public void setAngebotsIntervall(AngebotsIntervall angebotsIntervall) {
        this.angebotsIntervall = angebotsIntervall;
    }

    
    
}
