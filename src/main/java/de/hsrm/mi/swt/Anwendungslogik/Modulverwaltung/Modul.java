package de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
public class Modul {


    private int id;
    private String name;
    private String beschreibung;
    private int cpGesamt;
    private List<Kompetenz> kompetenzGesamt;
    private Fachsemester originalesFachsemester;
    private Fachsemester verschobenesFachsemester;
    private Fachsemester vorherigesFachsemester;
    private boolean bestanden;
    private List<Lehrveranstaltung> lehrveranstaltungenGesamt;
    public static final String SET_VERSCH_SEMESTER = "verschobenesSemester";
    private boolean falschVerschoben;
    

    /**
     * Konstruktor für ein Modul
     * @param id
     * @param name
     * @param beschreibung
     * @param cpGesamt
     * @param kompetenz
     * @param originalesFachsemester
     * @param verschobenesFachsemester
     * @param vorherigesFachsemester
     * @param bestanden
     * @param lehrveranstaltungenGesamt
     */
    public Modul(int id, String name, String beschreibung, int cpGesamt, List<Kompetenz> kompetenz,
            Fachsemester originalesFachsemester, Fachsemester verschobenesFachsemester, Fachsemester vorherigesFachsemester, boolean bestanden, List<Lehrveranstaltung> lehrveranstaltungenGesamt) {
        super();
        this.id = id;
        this.name = name;
        this.beschreibung = beschreibung;
        this.cpGesamt = cpGesamt;
        this.kompetenzGesamt = kompetenz;
        this.originalesFachsemester = originalesFachsemester;
        this.verschobenesFachsemester = verschobenesFachsemester;
        this.vorherigesFachsemester = vorherigesFachsemester;
        this.bestanden = bestanden;
        this.lehrveranstaltungenGesamt = lehrveranstaltungenGesamt;
        this.falschVerschoben = false;
    }

    //changeEvents für Modul verschieben -> Kopie von DragAndDropFxDing
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    
    /** 
     * Fügt den Property ChangeListener hinzu
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    
    /** 
     * Löscht den Property Change Listener
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }   

    
    /** 
     * Gibt den Namen des Moduls zurück
     * @return String
     */
    public String getName() {
        return name;
    }
    
    /** 
     * Gibt die Beschreibung des Moduls zurück
     * @return String
     */
    public String getBeschreibung() {
        return beschreibung;
    }
    
    /** 
     * Gibt die Gesamte CP Anzahl des Moduls zurück
     * @return int
     */
    public int getCpGesamt() {
        return cpGesamt;
    }
        
    /** 
     * Gibt eine Liste von Kompetenzen des Moduls zurück
     * @return List<Kompetenz>
     */
    public List<Kompetenz> getKompetenzGesamt() {
        return kompetenzGesamt;
    }
    
    /** 
     * Gibt eine Liste von Lehrveranstaltungen des Moduls zurück
     * @return List<Lehrveranstaltung>
     */
    public List<Lehrveranstaltung> getLehrveranstaltungenGesamt() {
        return lehrveranstaltungenGesamt;
    }
    
    /** 
     * Gibt die ID des Moduls zurück
     * @return int
     */
    public int getId() {
        return id;
    }
    
    /** 
     * Gibt das originale Fachsemester des Moduls zurück
     * @return Fachsemester
     */
    public Fachsemester getOriginalesFachsemester() {
        return originalesFachsemester;
    }
    
    /** 
     * Gibt das verschobene Fachsemester des Moduls zurück
     * @return Fachsemester
     */
    public Fachsemester getVerschobenesFachsemester() {
        return verschobenesFachsemester;
    }

    
    /** 
     * Setzt das originale Fachsemester des Moduls
     * @param verschobenesFachsemester
     */
    public void setVerschobenesFachsemester(Fachsemester verschobenesFachsemester) {
        Fachsemester pre = this.verschobenesFachsemester;
        this.verschobenesFachsemester = verschobenesFachsemester;
        this.pcs.firePropertyChange(SET_VERSCH_SEMESTER, pre, this.verschobenesFachsemester);
        System.out.println("set verschobenes Fachsemester: "+ verschobenesFachsemester.getid());
    }

    
    /** 
     * Gibt zurück, ob das Modul bestanden ist
     * @return boolean
     */
    public boolean isBestanden() {
        return bestanden;
    }

    
    /** 
     * Setzt das Modul, ob es bestanden ist oder nicht
     * @param bestanden
     */
    public void setBestanden(boolean bestanden) {
        this.bestanden = bestanden;
    }


    
    /** 
     * String ausgabe des Moduls
     * @return String
     */
    @Override
    public String toString() {
        return "Modul [ bestanden=" + bestanden + ", cpGesamt=" + cpGesamt + ", id="
                + id +", name=" + name + ", originalesFachsemester=" + originalesFachsemester.getid()
                + ", verschobenesFachsemester=" + verschobenesFachsemester.getid()
                + ", vorherigesFachsemester=" + vorherigesFachsemester.getid() + "]";
    }

    
    /** 
     * Gibt das Fachsemester des Moduls, in dem es vorher war, zurück
     * @return Fachsemester
     */
    public Fachsemester getVorherigesFachsemester() {
        return vorherigesFachsemester;
    }

    
    /** 
     * Setzt das vorherige Fachsemester des Moduls
     * @param vorherigesFachsemester
     */
    public void setVorherigesFachsemester(Fachsemester vorherigesFachsemester) {
        this.vorherigesFachsemester = vorherigesFachsemester;
    }

    
    /** 
     * Gibt zurück, ob das Modul falsch verschoben ist
     * @return boolean
     */
    public boolean isFalschVerschoben() {
        return falschVerschoben;
    }

    
    /** 
     * Setzt, ob das Modul falsch verschoben ist oder nicht
     * @param falschVerschoben
     */
    public void setFalschVerschoben(boolean falschVerschoben) {
        var pre = this.falschVerschoben;
        this.falschVerschoben = falschVerschoben;
        this.pcs.firePropertyChange("falschVerschoben", pre, falschVerschoben);
        
    }

    
    /** 
     * Gibt zurück, ob das Modul falsch verschoben ist
     * @return boolean
     */
    public boolean getFalschVerschoben(){
        return this.falschVerschoben;
    }

    

}
