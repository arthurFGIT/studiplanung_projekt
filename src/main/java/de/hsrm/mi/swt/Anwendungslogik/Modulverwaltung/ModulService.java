package de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung;

import java.util.HashMap;
import java.util.Map;

public class ModulService {

    private Modul aktuellesModul;

    private Map<Integer, Modul> modulMap = new HashMap<>();

    public ModulService(){

    }


    public Map<Integer, Modul> erzeugen(String dateiPfad){
        
        
        return modulMap;
    }

    public Modul holeModulmitId(int id){

        return modulMap.get(id);
    }
    
}
