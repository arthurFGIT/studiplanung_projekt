package de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung;

import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Modul;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.ModulService;

public class StudienplanService {

    private ModulService modulService;
    private CheckService checkService;
    private ErrorService errorService;


    public void ladePlan(int nutzerid){

    }

    public void verschiebeModul(int id, int x, int y){ //TODO ID oder ganzes Modul mitgeben

        
    //     boolean fortschritt = checkService.checkFortschrittsregel(modulService.holeModulmitId(id), zielSemester);
    //     if(fortschritt){
    //         boolean kompetenz = checkService.checkKompetenzen(modul, zielSemester);
    //         if(kompetenz){
    //             boolean semster = checkService.checkSemester(angebotsIntervall, neueFachsemester);
    //             if(semster){
    //                 Modul modul = modulService.holeModulmitId(id);
    //                 modul.setxKoordinate(x);
    //                 modul.setyKoordinate(y);
    //             }
    //             else{
    //                 errorService.getErrorMessages().add("Das Modul wird in diesem Semester nicht angeboten.");
    //             }

    //         }
    //         else{ //TODO: evtl anders gestalten, da Kompetenzen nur Empfehlung 
    //             errorService.getErrorMessages().add("Du benötigst Kompetenzen aus vorherigen Modulen, die du noch nicht hast.");
    //         }

    //     }
    //     else{
    //         errorService.getErrorMessages().add("Bitte beachte die Fortschrittsregelung.");
    //     }
        
        
        
    // }


    // public void speicherePlan(){
        
    }

    

    
}
