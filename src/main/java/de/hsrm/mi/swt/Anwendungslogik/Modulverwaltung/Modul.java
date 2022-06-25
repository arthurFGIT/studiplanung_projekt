package de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class Modul {

    private int id;
    private String name;
    private String beschreibung;
    // private Prüfungsleistung prüfungsleistung;
    private int cpGesamt;
    private List<Kompetenz> kompetenzGesamt;
    private Fachsemester originalesFachsemester; //TODO: Fachsemester wieder als Klasse, um Koordianten zu speichern
    private Fachsemester verschobenesFachsemester;
    private boolean bestanden;
    public static final String SET_VERSCH_SEMESTER = "verschobenesSemester"; //TODO: googlen was das macht

    
    public Modul(int id, String name, String beschreibung, int cpGesamt, List<Kompetenz> kompetenz,
            Fachsemester originalesFachsemester, Fachsemester verschobenesFachsemester, boolean bestanden) {
        this.id = id;
        this.name = name;
        this.beschreibung = beschreibung;
        // this.prüfungsleistung = prüfungsleistung;
        this.cpGesamt = cpGesamt;
        this.kompetenzGesamt = kompetenz;
        this.originalesFachsemester = originalesFachsemester;
        this.verschobenesFachsemester = verschobenesFachsemester;
        this.bestanden = bestanden;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    // public Prüfungsleistung getPrüfungsleistung() {
    //     return prüfungsleistung;
    // }

    // public void setPrüfungsleistung(Prüfungsleistung prüfungsleistung) {
    //     this.prüfungsleistung = prüfungsleistung;
    // }

    public int getCpGesamt() {
        return cpGesamt;
    }

    public void setCpGesamt(int cpGesamt) {
        this.cpGesamt = cpGesamt;
    }

    public List<Kompetenz> getKompetenzGesamt() {
        return kompetenzGesamt;
    }

    public void setKompetenzGesamt(List<Kompetenz> kompetenzGesamt) {
        this.kompetenzGesamt = kompetenzGesamt;
    }

    // public String getKompetenzGesamt() {
    //     return kompetenz;
    // }

    // public void setKompetenzGesamt(String kompetenz) {
    //     this.kompetenz = kompetenz;
    // }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    public Fachsemester getOriginalesFachsemester() {
        return originalesFachsemester;
    }

    public void setOriginalesFachsemester(Fachsemester originalesFachsemester) {
        this.originalesFachsemester = originalesFachsemester;
    }

    public Fachsemester getVerschobenesFachsemester() {
        return verschobenesFachsemester;
    }

    public void setVerschobenesFachsemester(Fachsemester verschobenesFachsemester) {
        Fachsemester pre = this.verschobenesFachsemester;
        this.verschobenesFachsemester = verschobenesFachsemester;
        this.pcs.firePropertyChange(SET_VERSCH_SEMESTER, pre, this.verschobenesFachsemester);
        System.out.println("set verschobenes Fachsemester: "+ verschobenesFachsemester);
    }

    public boolean isBestanden() {
        return bestanden;
    }

    public void setBestanden(boolean bestanden) {
        this.bestanden = bestanden;
    }



    //changeEvents für Modul verschieben -> Kopie von DragAndDropFxDing
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
}

    @Override
    public String toString() {
        return "Modul [beschreibung=" + beschreibung + ", bestanden=" + bestanden + ", cpGesamt=" + cpGesamt + ", id="
                + id + ", kompetenzGesamt=" + kompetenzGesamt + ", name=" + name + ", originalesFachsemester="
                + originalesFachsemester + ", verschobenesFachsemester=" + verschobenesFachsemester + "]";
    }


    
}
