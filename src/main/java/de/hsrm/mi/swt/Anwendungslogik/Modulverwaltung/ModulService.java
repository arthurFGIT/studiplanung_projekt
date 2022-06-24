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
        modulMap = new HashMap<>();
    }

    public Map<Integer, Modul> erzeugen(String dateipfad){
        try {
            File file = new File(dateipfad);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);

            NodeList modulList = document.getElementsByTagName("modul");
            for (int i = 0; i < modulList.getLength(); i++){
                int modulID = Integer.parseInt(document.getElementsByTagName("id").item(i).getTextContent());
                System.out.println("ModulID IN: " + modulID);
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

                // Originales Fachsemester
                int origFachsemesterID = Integer.parseInt(document.getElementsByTagName("origFachsemesterId").item(i).getTextContent());
                String origAngebotsIntervall = document.getElementsByTagName("origAngebotsIntervall").item(i).getTextContent();
                AngebotsIntervall angebotsIntervallOrig = null;
                if (origAngebotsIntervall.equalsIgnoreCase("winter")){
                    angebotsIntervallOrig = AngebotsIntervall.WINTER;
                }
                else if(origAngebotsIntervall.equalsIgnoreCase("sommer")){
                    angebotsIntervallOrig = AngebotsIntervall.SOMMER;
                }
                else if(origAngebotsIntervall.equalsIgnoreCase("wiso")){
                    angebotsIntervallOrig = AngebotsIntervall.WISO;
                }
                Fachsemester origFachsemester = new Fachsemester(origFachsemesterID, angebotsIntervallOrig);
                
                // verschobenes Fachsemester
                int verschFachsemesterID = Integer.parseInt(document.getElementsByTagName("verschFachsemesterId").item(i).getTextContent());
                String verschAngebotsIntervall = document.getElementsByTagName("verschAngebotsIntervall").item(i).getTextContent();
                AngebotsIntervall angebotsIntervallVersch = null;
                if (verschAngebotsIntervall.equalsIgnoreCase("winter")){
                    angebotsIntervallVersch = AngebotsIntervall.WINTER;
                }
                else if(verschAngebotsIntervall.equalsIgnoreCase("sommer")){
                    angebotsIntervallVersch = AngebotsIntervall.SOMMER;
                }
                else if(verschAngebotsIntervall.equalsIgnoreCase("wiso")){
                    angebotsIntervallVersch = AngebotsIntervall.WISO;
                }
                Fachsemester verschFachsemester = new Fachsemester(verschFachsemesterID, angebotsIntervallVersch);
                                
                boolean bestanden = Boolean.parseBoolean(document.getElementsByTagName("bestanden").item(i).getTextContent());
                int xKoordinate = Integer.parseInt(document.getElementsByTagName("xKoordinate").item(i).getTextContent());
                int yKoordinate = Integer.parseInt(document.getElementsByTagName("yKoordinate").item(i).getTextContent());

                neuesModul = new Modul(modulID, modulname, modulBeschreibung, cpGesamt, kompetenzListe, origFachsemester, verschFachsemester, bestanden, xKoordinate, yKoordinate);
                modulMap.put(i, neuesModul);
            }
            for(int key : modulMap.keySet()){
                System.out.println("ID: " + modulMap.get(key));
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
