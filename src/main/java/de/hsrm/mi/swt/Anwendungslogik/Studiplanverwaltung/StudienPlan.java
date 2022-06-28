package de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung;
import java.util.HashMap;
import java.util.Map;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Modul;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.ModulService;
import de.hsrm.mi.swt.main.App;

public class Studienplan {



    private Map<Integer, Studiensemester> semesterMap;
    private App app;
    private StudienplanService studienplanService;
    private ModulService modulService;
    private Map<Integer,Modul> modulMap;

    public static final String PLAN_ERNEUERT = "planErneuert";

    public Studienplan(App app){        
        this.app = app;
        this.studienplanService = app.getStudienplanService();
        this.modulService = app.getModulService();
        this.modulMap = modulService.getModulMap();
        this.semesterMap = new HashMap<>();
        createMap();        
    }

    
    public void createMap(){
        
        for(int i=1; i<= studienplanService.maxSemesterAnzahl(); i++){            
            Studiensemester studiensemester = new Studiensemester(app);
            for(int k : modulMap.keySet()){
                if(modulMap.get(k).getVerschobenesFachsemester().getid() == i){
                    studiensemester.add(modulMap.get(k));
                }                
            }
            semesterMap.put(i, studiensemester);
        }

    }




    public Studiensemester holeStudiensemesterMitId(int i) {
        return semesterMap.get(i);
    }


    public Map<Integer, Studiensemester> getSemesterMap() {
        return semesterMap;
    }


    public void setSemesterMap(Map<Integer, Studiensemester> semesterMap) {
        this.semesterMap = semesterMap;
    }


    



}

    
