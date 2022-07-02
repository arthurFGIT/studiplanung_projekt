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

    public boolean checkKompetenzen(Modul modul, Fachsemester zielFachsemester){ //TODO: wie machen wir die Zuordnung zwischen Fachsemester und Koordinaten
        boolean sendeMsg = false;
        List<Kompetenz> kompetenzen = modul.getKompetenzGesamt();
        for(int key : modulMap.keySet()) {
            Modul m = modulMap.get(key);
            if(m.getVerschobenesFachsemester().getid() < zielFachsemester.getid()){
                List<Kompetenz> modulkompList = m.getKompetenzGesamt();
                for (Kompetenz kompetenz : modulkompList){
                    for (Kompetenz k : kompetenzen){
                        if (kompetenz.getName().equals(k.getName())){
                            sendeMsg = true;
                        }
                    }
                }
            }
            
        }  
        
        if(sendeMsg){
            System.out.println("Wenn du das Modul "+modul.getName() + "dorthin verschiebst, fehlen dir wichtige Kompetenzen in den nächsten Semestern.");
            errorService.getObservableMessages().add("Wenn du das Modul "+ modul.getName() + " dorthin verschiebst, fehlen dir wichtige Kompetenzen in den nächsten Semestern.");      
            return false;                      
        }

        return true;
    }   
    
    public boolean checkFortschrittsregel(Modul modul, Fachsemester zielFachsemester){
        int modulCounter = 0;

        // Map die pro Standardsemester die Anzahl der Standardmodule im Standardcurriculum speichert
        Map<Integer, Integer> modulCounterMap = new HashMap<>();
        for (int i = 1; i <= SEMESTERANZAHL_STANDARDCURRICULUM; i++){
            for (int j = 0; j < modulService.getModulMap().size(); j++){
                if(modulService.getModulMap().get(j).getOriginalesFachsemester().getid() == i){
                    modulCounter ++;
                }
            }
            modulCounterMap.put(i, modulCounter);
            modulCounter = 0;
        }
        
        int horizont = 3;
        int ziel = zielFachsemester.getid();
        int original = modul.getOriginalesFachsemester().getid();

        if(original <= horizont && ziel <= horizont){
            return true;
        }

        int counter = 1;
        while(counter <= ziel){
            Studiensemester currentStudiensemester = modulService.getStudienplan().getSemesterMap().get(counter);
            List<Modul> currentModulliste = currentStudiensemester.getModulListe();
            for(Modul currentmodul : currentModulliste){
                int semester = currentmodul.getOriginalesFachsemester().getid();
                if(ziel > horizont){
                    errorService.getObservableMessages().add("Wenn du das Modul "+ modul.getName() + " verschiebst verletzt du die Fortschrittsregel.");
                    return false;
                }
                int zahl = modulCounterMap.get(semester);
                modulCounterMap.put(semester, zahl-1);
            }
            if(modulCounterMap.get(horizont - 2) == 0){
                horizont ++;
            }
            counter ++;
        }
        return true;
        
    }

    public boolean checkSemester(Modul modul, AngebotsIntervall angebotsIntervall, Fachsemester neueFachsemester){
        
        if(angebotsIntervall == neueFachsemester.getAngebotsIntervall() || neueFachsemester.getAngebotsIntervall() == AngebotsIntervall.WISO){
            System.out.println("Kann verschoben werden (Angebotsintervall).");
            return true;
        } else{
            System.out.println("Kann nicht verschoben werden (Angebotsintervall).");
            errorService.getObservableMessages().add("Das Modul "+ modul.getName() + " wird nicht in diesem Semester angeboten.");
            return false; 
        }
    }
    
}
