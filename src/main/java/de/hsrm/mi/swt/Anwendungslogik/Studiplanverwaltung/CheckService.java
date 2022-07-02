package de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.AngebotsIntervall;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Fachsemester;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Kompetenz;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Modul;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.ModulService;

public class CheckService {
    
    private ErrorService errorService;  
    private Map<Integer, Modul> modulMap;
    private ModulService modulService;
    private final int SEMESTERANZAHL_STANDARDCURRICULUM = 7;


    public CheckService(ModulService modulService, ErrorService errorService) {
        this.errorService = errorService;
        this.modulService = modulService;
        modulMap = modulService.getModulMap();
    }

    public void checkKompetenzen(Modul modul){
        List<Kompetenz> kompetenzen = modul.getKompetenzGesamt();
        // alle durchgehen -> größer gleich ziel.id => kompetenzen dadrin - name Modul
        for(Modul m : modulMap.values()) {
            if(m.getVerschobenesFachsemester().getid() >= modul.getVerschobenesFachsemester().getid()){

                for(Kompetenz komp : kompetenzen){
                    if(komp.getName().equals(m.getName())){
                        // errorService.getObservableMessages().add("Wenn du das Modul "+ modul.getName() + " dorthin verschiebst, fehlen dir wichtige Kompetenzen in den vorherigen Semestern.");      
                        // System.out.println("Wenn du das Modul "+modul.getName() + "dorthin verschiebst, fehlen dir wichtige Kompetenzen in den vorherigen Semestern.");

                        modul.setFalschVerschoben(true);
                    }
                }
                if (!modul.getFalschVerschoben()){
                    modul.setFalschVerschoben(false);
                }
             
            }
            
        }  
       
        
    }   

    public void checkFortschrittsregel(Modul modul){

        
        Fachsemester aktuellFachsemester = modul.getVerschobenesFachsemester();
        Fachsemester originalesFachsemester = modul.getOriginalesFachsemester();
        int horizont = 3;
        int mindestSemester = originalesFachsemester.getid() - horizont;

        if (originalesFachsemester.getid() <= horizont) {
            if (!modul.getFalschVerschoben()){
                modul.setFalschVerschoben(false);
            }

            return;
        }

        for (Modul m : modulMap.values()){
            if (m.getVerschobenesFachsemester().getid() >= aktuellFachsemester.getid()){
                if (m.getOriginalesFachsemester().getid() <= mindestSemester){
                    
                    errorService.getObservableMessages().add("Das Modul "+ modul.getName() + " verstößt gegen die Fortschrittsregel");
                    modul.setFalschVerschoben(true);
                    return;
                }
            }
        }

    }
    
    public void checkSemester(Modul modul){
        
        AngebotsIntervall angebotsIntervall = modul.getOriginalesFachsemester().getAngebotsIntervall();
        if(angebotsIntervall == modul.getVerschobenesFachsemester().getAngebotsIntervall() || angebotsIntervall == AngebotsIntervall.WISO){
            
            if (!modul.getFalschVerschoben()){
                modul.setFalschVerschoben(false);
            }

        } else{
            // System.out.println("Kann nicht verschoben werden (Angebotsintervall).");
            errorService.getObservableMessages().add("Das Modul "+ modul.getName() + " wird nicht in diesem Semester angeboten.");
            modul.setFalschVerschoben(true);
        }
    }
    
}
