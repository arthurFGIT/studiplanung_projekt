package de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung;

public class Lehrveranstaltung {

    private int workloadInCP;
    private int zeitaufwand;
    private boolean anwesenheitspflicht;
    private VeranstaltungsTyp veranstaltungsTyp;

    public Lehrveranstaltung(int workloadInCP, int zeitaufwand, boolean anwesenheitspflicht, AngebotsIntervall angebotsIntervall, VeranstaltungsTyp veranstaltungsTyp) {
        this.workloadInCP = workloadInCP;
        this.zeitaufwand = zeitaufwand;
        this.anwesenheitspflicht = anwesenheitspflicht;
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

    public VeranstaltungsTyp getVeranstaltungsTyp() {
        return veranstaltungsTyp;
    }

    public void setVeranstaltungsTyp(VeranstaltungsTyp veranstaltungsTyp) {
        this.veranstaltungsTyp = veranstaltungsTyp;
    }

    


    
    
}
