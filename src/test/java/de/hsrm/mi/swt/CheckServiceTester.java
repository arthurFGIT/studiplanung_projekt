package de.hsrm.mi.swt;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
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
import de.hsrm.mi.swt.main.App;

public class CheckServiceTester {

    private App app = new App();

    private ErrorService errorService = new ErrorService();  
    private ModulService modulService = new ModulService(app);

    private CheckService checkService = new CheckService(modulService, errorService);
    private Fachsemester erstes = new Fachsemester(1, AngebotsIntervall.WINTER);
    private Fachsemester zweites = new Fachsemester(2, AngebotsIntervall.SOMMER);
    private Fachsemester drittes = new Fachsemester(3, AngebotsIntervall.WISO);
    private Fachsemester viertes = new Fachsemester(4, AngebotsIntervall.SOMMER);
    private Fachsemester fuenftes = new Fachsemester(5, AngebotsIntervall.WINTER);
    private Fachsemester sechstes = new Fachsemester(6, AngebotsIntervall.SOMMER);
    private Fachsemester neuntes = new Fachsemester(9, AngebotsIntervall.SOMMER);
    private Fachsemester zehntes = new Fachsemester(10, AngebotsIntervall.WINTER);



    private Lehrveranstaltung lv = new Lehrveranstaltung(5, VeranstaltungsTyp.VORLESUNG, false);
    private Lehrveranstaltung lv2 = new Lehrveranstaltung(4, VeranstaltungsTyp.VORLESUNG, false);
    private List<Lehrveranstaltung> lvList = new ArrayList<>();
  
    private Kompetenz kompetenz = new Kompetenz("testModul");
    private Kompetenz kompetenz2 = new Kompetenz("testModul2");
    private List<Kompetenz> kompetenzList = new ArrayList<>();
    private List<Kompetenz> leereListe = new ArrayList<>();
    private List<Kompetenz> kompetenzList2 = new ArrayList<>();
    private Map<Integer, Modul> modulMap = new HashMap<>();

    public void fillmap(){
        modulMap.put(1,modul);
        modulMap.put(2,modul2);
        modulMap.put(3,modul3);
        modulMap.put(5,modul5);
        modulMap.put(6,modul6);
        modulMap.put(9,modul9);

        modulService.setModulMap(modulMap);
    }

    public void erzeugen(){
        lvList.add(lv);
        lvList.add(lv2);
        kompetenzList.add(kompetenz);
        kompetenzList2.add(kompetenz2);

        fillmap();
        checkService = new CheckService(modulService, errorService);

    } 

    
    
    

    private Modul modul = new Modul(1, "testModul", "Dies ist ein Test", 6,leereListe, erstes, erstes, erstes, false, lvList);

    private Modul modul2 = new Modul(2, "testModul2", "Dies ist ein Test", 4, kompetenzList2, zweites, zweites, zweites, false, lvList);

    private Modul modul6 = new Modul(3, "testModul3", "Dies ist ein Test", 5, kompetenzList2, sechstes, sechstes, sechstes, false, lvList);

    private Modul modul3 = new Modul(4, "testModul4", "Dies ist ein Test", 5, kompetenzList2, drittes, drittes, drittes, false, lvList);
    
    private Modul modul9 = new Modul(5, "testModul5", "Dies ist ein Test", 5, kompetenzList2, neuntes, neuntes, neuntes, false, lvList);

    private Modul modul5 = new Modul(6, "testModul5", "Dies ist ein Test", 5, kompetenzList2, fuenftes, fuenftes, fuenftes, false, lvList);

    

    @Test
    void vorabCheck(){
        erzeugen();
        assertNotNull(checkService);
    }

    @Test
    void checkSemesterTest(){
        modul.setVerschobenesFachsemester(zweites);
        checkService.checkSemester(modul);
        Assertions.assertTrue(modul.getFalschVerschoben());

        modul2.setVerschobenesFachsemester(viertes);
        checkService.checkSemester(modul2);
        Assertions.assertFalse(modul2.getFalschVerschoben());
        
        modul5.setVerschobenesFachsemester(zehntes);
        checkService.checkSemester(modul5);
        Assertions.assertFalse(modul5.getFalschVerschoben());  

    }

    @Test
    void checkFortschrittsregel(){
        
        erzeugen();

        modul2.setVerschobenesFachsemester(drittes);
        checkService.checkFortschrittsregel(modul2);
        Assertions.assertFalse(modul.getFalschVerschoben());

        modul2.setVerschobenesFachsemester(sechstes);
        checkService.checkFortschrittsregel(modul3);
        Assertions.assertFalse(modul3.getFalschVerschoben());

        checkService.checkFortschrittsregel(modul5);
        Assertions.assertTrue(modul5.getFalschVerschoben());

    }


    @Test
    void checkKompetenzenTest(){

        erzeugen();
        modul.setVerschobenesFachsemester(drittes);
        checkService.checkKompetenzen(modul2);
        Assertions.assertTrue(modul2.getFalschVerschoben());

        checkService.checkKompetenzen(modul);
        Assertions.assertFalse(modul.getFalschVerschoben());

    }

    
}
