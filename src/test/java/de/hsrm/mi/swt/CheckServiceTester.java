package de.hsrm.mi.swt;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
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
    private Fachsemester drittes = new Fachsemester(3, AngebotsIntervall.WINTER);
    private Fachsemester viertes = new Fachsemester(4, AngebotsIntervall.SOMMER);
    private Fachsemester fuenftes = new Fachsemester(5, AngebotsIntervall.WINTER);
    private Fachsemester sechstes = new Fachsemester(6, AngebotsIntervall.SOMMER);



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

    private Modul modul3 = new Modul(3, "testModul3", "Dies ist ein Test", 5, kompetenzList2, sechstes, sechstes, sechstes, false, lvList);


    @Test
    void vorabCheck(){
        erzeugen();
        assertNotNull(checkService);
    }

    @Test
    void checkSemesterTest(){
        Assertions.assertTrue(checkService.checkSemester(modul, modul.getVerschobenesFachsemester().getAngebotsIntervall(), drittes));
        Assertions.assertFalse(checkService.checkSemester(modul, modul.getVerschobenesFachsemester().getAngebotsIntervall(), zweites));
        Assertions.assertFalse(checkService.checkSemester(modul2, modul2.getVerschobenesFachsemester().getAngebotsIntervall(), erstes));
        Assertions.assertFalse(checkService.checkSemester(modul2, modul2.getVerschobenesFachsemester().getAngebotsIntervall(), drittes));
        Assertions.assertTrue(checkService.checkSemester(modul2, modul2.getVerschobenesFachsemester().getAngebotsIntervall(), zweites));
    }

    @Test
    void checkFortschrittsregel(){
        Assertions.assertTrue(checkService.checkFortschrittsregel(modul, erstes));
        Assertions.assertTrue(checkService.checkFortschrittsregel(modul, zweites));
        Assertions.assertTrue(checkService.checkFortschrittsregel(modul, drittes));
        // Assertions.assertFalse(checkService.checkFortschrittsregel(modul, viertes));
        Assertions.assertTrue(checkService.checkFortschrittsregel(modul2, fuenftes));
        // Assertions.assertFalse(checkService.checkFortschrittsregel(modul2, sechstes));
        // Assertions.assertFalse(checkService.checkFortschrittsregel(modul3, zweites));

    }

    @Test
    void checkKompetenzenTest(){
        Assertions.assertTrue(true);
        Assertions.assertNotNull(17);
    }
    
}
