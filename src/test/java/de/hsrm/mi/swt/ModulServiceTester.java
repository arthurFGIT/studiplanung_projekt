package de.hsrm.mi.swt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.AngebotsIntervall;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Fachsemester;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Kompetenz;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Lehrveranstaltung;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Modul;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.ModulService;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.VeranstaltungsTyp;
import de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung.CheckService;
import de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung.ErrorService;
import de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung.Studienplan;
import de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung.StudienplanService;
import de.hsrm.mi.swt.main.App;

public class ModulServiceTester {

    private App app = new App();
    private ModulService modulService = new ModulService(app);
    private Studienplan studienplan;
    private ErrorService errorService = new ErrorService();
    private CheckService checkService = new CheckService(modulService, errorService);
    private StudienplanService studienplanService = new StudienplanService(modulService, checkService, errorService);



    private Map<Integer,Modul> modulMap = new HashMap<>();

    private Fachsemester erstes = new Fachsemester(1, AngebotsIntervall.WINTER);
    private Fachsemester zweites = new Fachsemester(2, AngebotsIntervall.SOMMER);
    private Fachsemester drittes = new Fachsemester(3, AngebotsIntervall.WINTER);
    

    private Lehrveranstaltung lv = new Lehrveranstaltung(5, VeranstaltungsTyp.VORLESUNG, false);
    private Lehrveranstaltung lv2 = new Lehrveranstaltung(4, VeranstaltungsTyp.VORLESUNG, false);
    private List<Lehrveranstaltung> lvList = new ArrayList<>();
  
    private Kompetenz kompetenz = new Kompetenz("");
    private Kompetenz kompetenz2 = new Kompetenz("testModul");
    private List<Kompetenz> kompetenzList = new ArrayList<>();
    private List<Kompetenz> kompetenzList2 = new ArrayList<>();

    

    public void erzeugen(){
        lvList.add(lv);
        lvList.add(lv2);
        kompetenzList.add(kompetenz);
        kompetenzList2.add(kompetenz2);
    }
    
    private Modul modul = new Modul(1, "testModul", "Dies ist ein Test", 6,kompetenzList, erstes, erstes, erstes, false, lvList);
    private Modul modul2 = new Modul(2, "testModul2", "Dies ist ein Test", 4, kompetenzList2, zweites, zweites, zweites, false, lvList);
    private Modul modul3 = new Modul(3, "testModul3", "Dies ist ein Test", 5, kompetenzList2, drittes, drittes, drittes, false, lvList);

    @BeforeEach
    void putInMaps(){
        erzeugen();
        modulMap.put(0,modul);
        modulMap.put(1,modul2);
        modulMap.put(2,modul3);
        modulService.setModulMap(modulMap);
        app.setModulService(modulService);
        app.setStudienplanService(studienplanService);
        studienplanService.setModulMap(modulMap);
        studienplan = new Studienplan(app); 

    }
   
    @Test
    void vorabCheck(){
        modulService.setStudienplan(studienplan);
        Assertions.assertNotNull(modulService);
    }


    @Test
    void checkModulErzeugen(){
        String dateipfad = "./src/main/resources/curriculum.xml";
        modulMap = modulService.erzeugen(dateipfad);
        Assertions.assertInstanceOf(Modul.class, modulMap.get(0));
    }
    
    
}