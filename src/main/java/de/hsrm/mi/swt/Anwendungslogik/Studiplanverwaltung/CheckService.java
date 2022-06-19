package de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung;

import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.AngebotsIntervall;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Fachsemester;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Modul;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.ModulService;

public class CheckService {
    
    private ModulService modulService;


    public boolean checkKompetenzen(Modul modul, Fachsemester neuesFachsemester, Fachsemester altesFachsemester){ //TODO: wie machen wir die Zuordnung zwischen Fachsemester und Koordinaten
        return false;
    }   
    
    public boolean checkFortschrittsregel(Modul modul, Fachsemester aktFachsemester, Fachsemester neuesFachsemester, Fachsemester altesFachsemester){
        return false;
    }

    public boolean checkSemester(AngebotsIntervall angebotsIntervall, Fachsemester neueFachsemester){
        return false;
    }
    
}
