package de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung;

import java.util.ArrayList;
import java.util.List;

import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Modul;

public class Studienfortschritt {

    private List<Modul> abgeschlosseneModule = new ArrayList<>();
    private List<Modul> nichtAbgeschlosseneModule = new ArrayList<>();
    private int individuelleSemesterZahl;

    
    public Studienfortschritt(List<Modul> abgeschlosseneModule, List<Modul> nichtAbgeschlosseneModule,
            int individuelleSemesterZahl) {
        this.abgeschlosseneModule = abgeschlosseneModule;
        this.nichtAbgeschlosseneModule = nichtAbgeschlosseneModule;
        this.individuelleSemesterZahl = individuelleSemesterZahl;
    }


    public List<Modul> getAbgeschlosseneModule() {
        return abgeschlosseneModule;
    }


    public void setAbgeschlosseneModule(List<Modul> abgeschlosseneModule) {
        this.abgeschlosseneModule = abgeschlosseneModule;
    }


    public List<Modul> getNichtAbgeschlosseneModule() {
        return nichtAbgeschlosseneModule;
    }


    public void setNichtAbgeschlosseneModule(List<Modul> nichtAbgeschlosseneModule) {
        this.nichtAbgeschlosseneModule = nichtAbgeschlosseneModule;
    }


    public int getIndividuelleSemesterZahl() {
        return individuelleSemesterZahl;
    }


    public void setIndividuelleSemesterZahl(int individuelleSemesterZahl) {
        this.individuelleSemesterZahl = individuelleSemesterZahl;
    }


    
}
