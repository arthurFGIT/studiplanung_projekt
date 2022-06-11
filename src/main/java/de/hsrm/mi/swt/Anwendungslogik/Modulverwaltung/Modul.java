package de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung;

import java.util.ArrayList;
import java.util.List;

public class Modul {

    private int id;
    private String name;
    private String beschreibung;
    // private Prüfungsleistung prüfungsleistung;
    private int cpGesamt;
    private List<Kompetenz> kompetenzGesamt; // TODO: Muss eine Liste sein
    private Fachsemester fachsemester;

    
    public Modul(int id, String name, String beschreibung, int cpGesamt,
            Fachsemester fachsemester) {
        this.name = name;
        this.beschreibung = beschreibung;
        // this.prüfungsleistung = prüfungsleistung;
        this.cpGesamt = cpGesamt;
        this.kompetenzGesamt = new ArrayList<>();
        this.fachsemester = fachsemester;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Fachsemester getFachsemester() {
        return fachsemester;
    }

    public void setFachsemester(Fachsemester fachsemester) {
        this.fachsemester = fachsemester;
    }



    
}
