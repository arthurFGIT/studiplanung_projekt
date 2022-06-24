package de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung;

import java.util.List;
import java.util.Map;

import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.AngebotsIntervall;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Fachsemester;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Kompetenz;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Modul;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.ModulService;

public class CheckService {
    
    private ErrorService errorService;  
    Map<Integer, Modul> modulMap;


    public CheckService(ModulService modulService, ErrorService errorService) {
        this.errorService = errorService;
        modulMap = modulService.getModulMap();
    }

    public void checkKompetenzen(Modul modul, Fachsemester zielFachsemester){ //TODO: wie machen wir die Zuordnung zwischen Fachsemester und Koordinaten

        List<Kompetenz> kompetenzen = modul.getKompetenzGesamt();
        for(int key : modulMap.keySet()) {
            Modul m = modulMap.get(key);
            if(m.getVerschobenesFachsemester().getid() < zielFachsemester.getid()){
                List<Kompetenz> modulkompList = m.getKompetenzGesamt();
                for (Kompetenz kompetenz : modulkompList){
                    for (Kompetenz k : kompetenzen){
                        if (kompetenz.getName().equals(k.getName())){
                            errorService.getErrorMessages().add("Wenn du das Modul "+modul.getName() + "dorthin verschiebst, fehlen dir wichtige Kompetenzen in den nächsten Semestern.");
                        }
                    }
                }
            }
            
        }        
    }   
    
    public boolean checkFortschrittsregel(Modul modul, Fachsemester zielFachsemester){
        
        int ziel = zielFachsemester.getid();
        int bereich = ziel - modul.getOriginalesFachsemester().getid();

        if (bereich < 3){
            if (bereich > 1) {
                for (int key : modulMap.keySet()){
                    Modul m = modulMap.get(key);
                    if (Math.abs(m.getOriginalesFachsemester().getid() - m.getVerschobenesFachsemester().getid()) > 1){
                        return false; 
                    }
                }
            }
            return true;
        }
        else{
            return false;
        }
        
    }

    public boolean checkSemester(AngebotsIntervall angebotsIntervall, Fachsemester neueFachsemester){
        
        if(angebotsIntervall == neueFachsemester.getAngebotsIntervall() || neueFachsemester.getAngebotsIntervall() == AngebotsIntervall.WISO){
            return true;
        } else{
            errorService.getErrorMessages().add("Modul wird nicht in diesem Semester angeboten.");
            return false; 
        }
    }
    
}
