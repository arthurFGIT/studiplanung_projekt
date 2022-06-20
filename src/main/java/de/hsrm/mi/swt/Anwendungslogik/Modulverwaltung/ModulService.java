package de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;


public class ModulService {

    private Modul aktuellesModul;
    private Modul neuesModul;

    private Map<Integer, Modul> modulMap;

    public ModulService(){

    }

    public Map<Integer, Modul> erzeugen(String dateipfad){
        try {
            modulMap = new HashMap<>();
            File file = new File(dateipfad);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);

            NodeList modulList = document.getElementsByTagName("modul");
            for (int i = 0; i < modulList.getLength(); i++){
                int modulID = Integer.parseInt(document.getElementsByTagName("id").item(i).getTextContent());
                String modulname = document.getElementsByTagName("name").item(i).getTextContent();
                String modulBeschreibung = document.getElementsByTagName("beschreibung").item(i).getTextContent();
                int cpGesamt = Integer.parseInt(document.getElementsByTagName("cpGesamt").item(i).getTextContent());
                NodeList kompetenzList = document.getElementsByTagName("kompetenz");
                
                List<Kompetenz> kompetenzListe = new ArrayList<>();
                for (int j = 0; j < kompetenzList.getLength(); j++){
                    String kompetenz = document.getElementsByTagName("kompetenz").item(j).getTextContent(); 
                    Kompetenz kompetenzNew = new Kompetenz(kompetenz);
                    kompetenzListe.add(kompetenzNew);
                }
                int fachsemester = Integer.parseInt(document.getElementsByTagName("fachsemester").item(i).getTextContent());
                int xKoordinate = Integer.parseInt(document.getElementsByTagName("xKoordinate").item(i).getTextContent());
                int yKoordinate = Integer.parseInt(document.getElementsByTagName("yKoordinate").item(i).getTextContent());

                neuesModul = new Modul(modulID, modulname, modulBeschreibung, cpGesamt, kompetenzListe, fachsemester, xKoordinate, yKoordinate);
                modulMap.put(i, neuesModul);
            }

         } catch (Exception e) {
            e.printStackTrace();
         }
         return modulMap;
    }

    public Modul holeModulmitId(int id){
        return modulMap.get(id);
    }

    public Modul getAktuellesModul() {
        return aktuellesModul;
    }

    public Modul getNeuesModul() {
        return neuesModul;
    }

    public Map<Integer, Modul> getModulMap() {
        return modulMap;
    }
    
}
