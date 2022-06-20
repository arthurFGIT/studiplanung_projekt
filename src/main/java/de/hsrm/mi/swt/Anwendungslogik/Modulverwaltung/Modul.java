package de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class Modul {

    private int id;
    private String name;
    private String beschreibung;
    // private Prüfungsleistung prüfungsleistung;
    private int cpGesamt;
    private List<Kompetenz> kompetenzGesamt;
    private String kompetenz;
    private int fachsemester; //TODO: Fachsemester wieder als Klasse, um Koordianten zu speichern
    private int xKoordinate;
    private int yKoordinate;
    public static final String SET_X = "x"; //TODO: googlen was das macht
    public static final String SET_Y = "y";

    
    public Modul(int id, String name, String beschreibung, int cpGesamt, List<Kompetenz> kompetenz,
            int fachsemester, int xKoordinate, int yKoordinate) {
        this.name = name;
        this.beschreibung = beschreibung;
        // this.prüfungsleistung = prüfungsleistung;
        this.cpGesamt = cpGesamt;
        this.kompetenzGesamt = new ArrayList<>();
        // this.kompetenz = kompetenz;
        this.fachsemester = fachsemester;
        this.xKoordinate = xKoordinate;
        this.yKoordinate = yKoordinate;
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

    public int getFachsemester() {
        return fachsemester;
    }

    public void setFachsemester(int fachsemester) {
        this.fachsemester = fachsemester;
    }

    public int getxKoordinate() {
        return xKoordinate;
    }

    public void setxKoordinate(int xKoordinate) {
        int pre = this.xKoordinate;
        this.xKoordinate = xKoordinate;
        this.pcs.firePropertyChange(SET_X, pre, this.xKoordinate);
    }

    public int getyKoordinate() {
        return yKoordinate;
    }

    public void setyKoordinate(int yKoordinate) {
        int pre = this.yKoordinate;
        this.yKoordinate = yKoordinate;
        this.pcs.firePropertyChange(SET_Y, pre, this.yKoordinate);
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
        return "Modul [beschreibung=" + beschreibung + ", cpGesamt=" + cpGesamt + ", fachsemester=" + fachsemester
                + ", id=" + id + ", kompetenzGesamt=" + kompetenzGesamt + ", name=" + name + ", xKoordinate="
                + xKoordinate + ", yKoordinate=" + yKoordinate + "]";
    }


    
}
