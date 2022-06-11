package de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung;

public class Lehrveranstaltung {

    private int workloadInCP;
    private int zeitaufwand;
    private boolean anwesenheitspflicht;
    private AngebotsIntervall angebotsIntervall;
    private VeranstaltungsTyp veranstaltungsTyp;

    public Lehrveranstaltung(int workloadInCP, int zeitaufwand, boolean anwesenheitspflicht, AngebotsIntervall angebotsIntervall, VeranstaltungsTyp veranstaltungsTyp) {
        this.workloadInCP = workloadInCP;
        this.zeitaufwand = zeitaufwand;
        this.anwesenheitspflicht = anwesenheitspflicht;
        this.angebotsIntervall = angebotsIntervall;
        this.veranstaltungsTyp = veranstaltungsTyp;
    }

    public int getWorkloadInCP() {
        return workloadInCP;
    }

    public void setWorkloadInCP(int workloadInCP) {
        this.workloadInCP = workloadInCP;
    }

    public int getZeitaufwand() {
        return zeitaufwand;
    }

    public void setZeitaufwand(int zeitaufwand) {
        this.zeitaufwand = zeitaufwand;
    }

    public boolean isAnwesenheitspflicht() {
        return anwesenheitspflicht;
    }

    public void setAnwesenheitspflicht(boolean anwesenheitspflicht) {
        this.anwesenheitspflicht = anwesenheitspflicht;
    }

    public AngebotsIntervall getAngebotsIntervall() {
        return angebotsIntervall;
    }

    public void setAngebotsIntervall(AngebotsIntervall angebotsIntervall) {
        this.angebotsIntervall = angebotsIntervall;
    }

    public VeranstaltungsTyp getVeranstaltungsTyp() {
        return veranstaltungsTyp;
    }

    public void setVeranstaltungsTyp(VeranstaltungsTyp veranstaltungsTyp) {
        this.veranstaltungsTyp = veranstaltungsTyp;
    }

    


    
    
}
