package de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung;

public class Lehrveranstaltung {

    private int workloadInCP;
    private VeranstaltungsTyp veranstaltungsTyp;
    private boolean bestanden;

    /**
     * Konstruktor für eine Lehrveranstaltung
     * @param workloadInCP
     * @param veranstaltungsTyp
     * @param bestanden
     */
    public Lehrveranstaltung(int workloadInCP, VeranstaltungsTyp veranstaltungsTyp, boolean bestanden) {
        this.workloadInCP = workloadInCP;
        this.veranstaltungsTyp = veranstaltungsTyp;
        this.bestanden = bestanden;
    }
    
    /** 
     * Gibt den Workload in CP zurück
     * @return int
     */
    public int getWorkloadInCP() {
        return workloadInCP;
    }

    
    /** 
     * Gibt den Veranstaltungstyp als Enum zurück
     * @return VeranstaltungsTyp
     */
    public VeranstaltungsTyp getVeranstaltungsTyp() {
        return veranstaltungsTyp;
    }
    
    /**
     * Setzt den Veranstaltungstyp 
     * @param veranstaltungsTyp
     */
    public void setVeranstaltungsTyp(VeranstaltungsTyp veranstaltungsTyp) {
        this.veranstaltungsTyp = veranstaltungsTyp;
    }

    
    /** 
     * Gibt zurück, ob die Lehrveranstaltung bestanden ist
     * @return boolean
     */
    public boolean isBestanden() {
        return bestanden;
    }
    
    /** 
     * Setzt die Lehrveranstaltung auf bestanden
     * @param bestanden
     */
    public void setBestanden(boolean bestanden) {
        this.bestanden = bestanden;
    } 
    
}
