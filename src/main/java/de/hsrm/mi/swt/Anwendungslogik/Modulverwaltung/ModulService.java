package de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung.Studienplan;
import de.hsrm.mi.swt.main.App;
/**
 * ModulService, der Module erzeugt, und den Stundenplan erzeugt und zur Verfuegung stellt
 * @author Marie Bohnert, Beate Arnold, Arthur Fieguth
 */
public class ModulService {

    private Modul aktuellesModul;
    private Modul neuesModul;
    private App app;
    private Studienplan studienplan;
    private Map<Integer, Modul> modulMap;

    /**
     * Konstruktor für den Modulservice. Erstellt eine leere modulMap, die alle Module verwaltet
     * @param app : Application wird mitgegeben
     */
    public ModulService(App app){
        this.app = app;
        modulMap = new HashMap<>();
    } 

    
    /** 
     * Aus der Datei mit dem Dateipfad werden Module erstellt und der ModulMap hinzugefügt und zurückgegeben
     * @param dateipfad : Dateipfad der XML Datei
     * @return Map<Integer, Modul> : Gibt Map der Module zurück
     */
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
                String modulname = document.getElementsByTagName("name").item(i).getTextContent();
                String modulBeschreibung = document.getElementsByTagName("beschreibung").item(i).getTextContent();
                int cpGesamt = Integer.parseInt(document.getElementsByTagName("cpGesamt").item(i).getTextContent());
                                
                NodeList kompetenzList = document.getElementsByTagName("kompetenzen").item(i).getChildNodes();
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

                // verschobenes Fachsemester
                int vorherigesFachsemesterID = Integer.parseInt(document.getElementsByTagName("vorherigesFachsemesterId").item(i).getTextContent());
                String vorherigesAngebotsIntervall = document.getElementsByTagName("vorherigesAngebotsIntervall").item(i).getTextContent();
                AngebotsIntervall angebotsIntervallVorherig = null;
                if (vorherigesAngebotsIntervall.equalsIgnoreCase("winter")){
                    angebotsIntervallVorherig = AngebotsIntervall.WINTER;
                }
                else if(vorherigesAngebotsIntervall.equalsIgnoreCase("sommer")){
                    angebotsIntervallVorherig = AngebotsIntervall.SOMMER;
                }
                else if(vorherigesAngebotsIntervall.equalsIgnoreCase("wiso")){
                    angebotsIntervallVorherig = AngebotsIntervall.WISO;
                }
                Fachsemester vorherigesFachsemester = new Fachsemester(vorherigesFachsemesterID, angebotsIntervallVorherig);
                                
                boolean bestanden = Boolean.parseBoolean(document.getElementsByTagName("bestanden").item(i).getTextContent());

                // Lehrveranstaltungen
                int workloadInCP = 0;
                String veranstaltungsTypString = "";
                VeranstaltungsTyp veranstaltungsTyp = null;
                boolean lehrveranstaltungBestanden = false;

                List<String> nodes;
                NodeList childreeen;
                NodeList children = document.getElementsByTagName("lehrveranstaltungen").item(i).getChildNodes();
                List<Lehrveranstaltung> lehrveranstaltungsListe = new ArrayList<>();
                for(int y=1; y < children.getLength(); y++) {
                    if(children.item(y).getNodeType() == 1 || children.item(y).getNodeType() == 3) {
                        nodes = new ArrayList<>();
                        childreeen = children.item(y).getChildNodes();

                        
                        for(int z = 0; z < childreeen.getLength(); z++){
                            if(childreeen.item(z).getNodeType()== Node.ELEMENT_NODE) {
                                nodes.add(childreeen.item(z).getTextContent());
                            }
                        } 
                        if(nodes.size() != 0){
                            workloadInCP = Integer.parseInt(nodes.get(0)); 
                            veranstaltungsTypString = nodes.get(1);
                            lehrveranstaltungBestanden = Boolean.parseBoolean(nodes.get(2));
                            veranstaltungsTyp = null;
                            if (veranstaltungsTypString.equalsIgnoreCase("praktikum")){
                                veranstaltungsTyp = VeranstaltungsTyp.PRAKTIKUM;
                            }
                            else if(veranstaltungsTypString.equalsIgnoreCase("vorlesung")){
                                veranstaltungsTyp = VeranstaltungsTyp.VORLESUNG;
                            }
                            else if(veranstaltungsTypString.equalsIgnoreCase("uebung")){
                                veranstaltungsTyp = VeranstaltungsTyp.UEBUNG;
                            }
                            else if(veranstaltungsTypString.equalsIgnoreCase("portfolio")){
                                veranstaltungsTyp = VeranstaltungsTyp.PORTFOLIO;
                            }
                            else if(veranstaltungsTypString.equalsIgnoreCase("listenfach")){
                                veranstaltungsTyp = VeranstaltungsTyp.LISTENFACH;
                            }
                            Lehrveranstaltung lehrveranstaltungsObj = new Lehrveranstaltung(workloadInCP, veranstaltungsTyp, lehrveranstaltungBestanden);
                            lehrveranstaltungsListe.add(lehrveranstaltungsObj);
    
                        }
                     }
                }

                neuesModul = new Modul(modulID, modulname, modulBeschreibung, cpGesamt, kompetenzListe, origFachsemester, verschFachsemester, vorherigesFachsemester, bestanden, lehrveranstaltungsListe);
                modulMap.put(i, neuesModul);
            }

         } catch (Exception e) {
            e.printStackTrace();
         }

         studienplan = new Studienplan(this.app);
         return modulMap;
    }



    /** 
     * Gibt die ModulMap zurück
     * @return Map<Integer, Modul> : HashMap aus allen erzeugten Modulen
     */
    public Map<Integer, Modul> getModulMap() {
        return modulMap;
    }

    
    /** 
     * Gibt den Studienplan zurück
     * @return Studienplan : Gibt ein Objekt der Studienplan-Klasse zurück
     */
    public Studienplan getStudienplan() {
        return studienplan;
    }    
}
