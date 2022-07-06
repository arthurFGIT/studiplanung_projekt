package de.hsrm.mi.swt;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Modul;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.ModulService;
import de.hsrm.mi.swt.main.App;

public class ModulServiceTester {

    private App app = new App();
    private ModulService modulService = new ModulService(app);

    private Map<Integer,Modul> modulMap = new HashMap<>();


    @Test
    void vorabCheck(){
        Assertions.assertNotNull(modulService);
    }


    @Test
    void checkModulErzeugen(){
        String dateipfad = "test.xml";
        modulMap = modulService.erzeugen(dateipfad);
        Assertions.assertInstanceOf(Modul.class, modulMap.get(0));
    }
    
    
}