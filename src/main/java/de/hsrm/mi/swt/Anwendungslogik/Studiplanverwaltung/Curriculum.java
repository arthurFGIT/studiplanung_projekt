package de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Modul;

import java.util.ArrayList;
import java.util.List;


public class Curriculum {

    private List<Modul> modulListe = new ArrayList<>();

    public Curriculum(List<Modul> modulListe) {
        this.modulListe = modulListe;
    }

    public List<Modul> getModulListe() {
        return modulListe;
    }

    public void setModulListe(List<Modul> modulListe) {
        this.modulListe = modulListe;
    }
    

    
}
