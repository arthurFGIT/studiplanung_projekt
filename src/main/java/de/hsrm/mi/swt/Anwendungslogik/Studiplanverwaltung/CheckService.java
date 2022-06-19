package de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung;

import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.AngebotsIntervall;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Modul;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.ModulService;

public class CheckService {
    
    private ModulService modulService;


    public boolean checkKompetenzen(Modul modul, int zielFachsemester){ //TODO: wie machen wir die Zuordnung zwischen Fachsemester und Koordinaten
        return false;
    }   
    
    public boolean checkFortschrittsregel(Modul modul, int zielFachsemester){
        return false;
    }

    public boolean checkSemester(AngebotsIntervall angebotsIntervall, int neueFachsemester){
        return false;
    }
    
}
