package de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung;

import java.io.File;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Kompetenz;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Modul;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.ModulService;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
/**
 * StudienplanService, stellt die Verwaltung des Studienplans zur Verfügung
 * @author Marie Bohnert, Beate Arnold, Arthur Fieguth
 */
public class StudienplanService {

    private ModulService modulService;
    private CheckService checkService;
    private ErrorService errorService;
    private Map<Integer, Modul> modulMap;
    private int maxSemesterAnzahl;
    private IntegerProperty propertyCP;
    private int maxCP;


    public static final String xmlFilePath = "moduleIndividual.xml";   


    /**
     * Konstruktor für den StudienplanService -> bekommt die alle Services mitgegeben
     * @param modulService 
     * @param checkService
     * @param errorService
     */
    public StudienplanService(ModulService modulService, CheckService checkService, ErrorService errorService) {
        this.modulService = modulService;
        this.checkService = checkService;
        this.errorService = errorService;
        modulMap = modulService.getModulMap();
        maxSemesterAnzahl = 0;
        propertyCP = new SimpleIntegerProperty();
    }
    
    /** 
     * Berechnet die MaxSemesterAnzahl, die aus den Modulen errechnet wird
     * @return int : Anzahl der Semester
     */
    public int maxSemesterAnzahl(){
        maxSemesterAnzahl = modulMap.get(0).getVerschobenesFachsemester().getid();
        for(int k : modulMap.keySet()){
            if(modulMap.get(k).getVerschobenesFachsemester().getid() > maxSemesterAnzahl){
                maxSemesterAnzahl = modulMap.get(k).getVerschobenesFachsemester().getid();
            }
        }
        return maxSemesterAnzahl;
    }
    
    /** 
     * Fügt ein Semester hinzu und gibt die Anzahl zurück
     * @return int : Semesteranzahl
     */
    public int addSemesterAnzahl(){
        return maxSemesterAnzahl()+1;
    }
    
    /** 
     * Berechnet die Maximal zu erreichende CP Zahl
     * @return int : Gibt die Anzahl zurück
     */
    public int calcMaxCP(){
        int maxCP = 0;
        for(int k : modulMap.keySet()){
            maxCP += modulMap.get(k).getCpGesamt();
        }
        return maxCP;
    }
    
    /** 
     * Berechnet die schon erreichte CP Anzahl
     * @return IntegerProperty : Gibt es als IntegerProperty zurück
     */
    public IntegerProperty calcActCP(){
        propertyCP.set(0);
        int cps = 0;
        for(int k : modulMap.keySet()){
            
            if(modulMap.get(k).isBestanden()){
                System.out.println("Add CP: " + modulMap.get(k).getCpGesamt());
                cps += modulMap.get(k).getCpGesamt();
                propertyCP.set(cps);
                System.out.println(propertyCP.getValue());
            }
            else{
                for(int y = 0 ;y < modulMap.get(k).getLehrveranstaltungenGesamt().size(); y++){
                    if(modulMap.get(k).getLehrveranstaltungenGesamt().get(y).isBestanden()){
                    cps += modulMap.get(k).getLehrveranstaltungenGesamt().get(y).getWorkloadInCP();
                    propertyCP.set(cps);
                }
            }
        }
    }
        return propertyCP;
    }  
    
    /** 
     * Gibt die CP Anzahl als Property zurück
     * @return IntegerProperty
     */
    public IntegerProperty getPropertyCP() {
        return propertyCP;
    }
    
    /** 
     * Setzt die CP Anzahl
     * @param propertyCP
     */
    public void setPropertyCP(IntegerProperty propertyCP) {
        this.propertyCP = propertyCP;
    }    
    
    /** 
     * Gibt die maximale CP Anzahl zurück
     * @return int
     */
    public int getMaxCP() {
        return maxCP;
    }

    /**
     * Checkt beim verschieben nochmal alle Module, ob die Verschiebung möglich ist
     */
    public void checkAll(){
        for(Modul m : modulMap.values()){
            
            m.setFalschVerschoben(false);
            checkService.checkSemester(m);						
            checkService.checkFortschrittsregel(m); 
            checkService.checkKompetenzen(m);       

        }
    }

    /**
     * Erstellt eine XML Datei von dem aktuellen Studienplan und speichert diese 
     * Wird beim neustart des Programms dann automatisch geladen
     */
    public void speicherePlan(){
        try {

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
 
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
 
            Document document = documentBuilder.newDocument();

            // root element
            Element root = document.createElement("module");
            document.appendChild(root);

            for(int key : modulMap.keySet()){
                Modul m = modulMap.get(key);
                // modul element
                Element modul = document.createElement("modul");
                root.appendChild(modul);


                // id element
                Element id = document.createElement("id");
                id.appendChild(document.createTextNode(String.valueOf(m.getId())));
                modul.appendChild(id);

                // name element
                Element name = document.createElement("name");
                name.appendChild(document.createTextNode(m.getName()));
                modul.appendChild(name);

                // beschreibung element
                Element beschreibung = document.createElement("beschreibung");
                beschreibung.appendChild(document.createTextNode(m.getBeschreibung()));
                modul.appendChild(beschreibung);

                // beschreibung element
                Element cpGesamt = document.createElement("cpGesamt");
                cpGesamt.appendChild(document.createTextNode(String.valueOf(m.getCpGesamt())));
                modul.appendChild(cpGesamt);

                // kompetenzen element
                Element kompetenzen = document.createElement("kompetenzen");
                modul.appendChild(kompetenzen);

                for(Kompetenz k : m.getKompetenzGesamt()){
                    // kompetenz element
                    Element kompetenz = document.createElement("kompetenz");
                    kompetenz.appendChild(document.createTextNode(k.getName()));
                    kompetenzen.appendChild(kompetenz);
                }

                // originales Fachsemester
                // ID
                Element origFachsemesterId = document.createElement("origFachsemesterId");
                origFachsemesterId.appendChild(document.createTextNode(String.valueOf(m.getOriginalesFachsemester().getid())));
                modul.appendChild(origFachsemesterId);

                // AngebotsIntervall
                Element origAngebotsIntervall = document.createElement("origAngebotsIntervall");
                origAngebotsIntervall.appendChild(document.createTextNode(m.getOriginalesFachsemester().getAngebotsIntervall().getName()));
                modul.appendChild(origAngebotsIntervall);
                

                // verschobenes Fachsemester
                // ID
                Element verschFachsemesterId = document.createElement("verschFachsemesterId");
                verschFachsemesterId.appendChild(document.createTextNode(String.valueOf(m.getVerschobenesFachsemester().getid())));
                modul.appendChild(verschFachsemesterId);

                // AngebotsIntervall
                Element verschAngebotsIntervall = document.createElement("verschAngebotsIntervall");
                verschAngebotsIntervall.appendChild(document.createTextNode(m.getVerschobenesFachsemester().getAngebotsIntervall().getName()));
                modul.appendChild(verschAngebotsIntervall);

                
                // vorheriges Fachsemester
                // ID
                Element vorherigesFachsemesterId = document.createElement("vorherigesFachsemesterId");
                vorherigesFachsemesterId.appendChild(document.createTextNode(String.valueOf(m.getVorherigesFachsemester().getid())));
                modul.appendChild(vorherigesFachsemesterId);

                // AngebotsIntervall
                Element vorherigesAngebotsIntervall = document.createElement("vorherigesAngebotsIntervall");
                vorherigesAngebotsIntervall.appendChild(document.createTextNode(m.getVorherigesFachsemester().getAngebotsIntervall().getName()));
                modul.appendChild(vorherigesAngebotsIntervall);

                // Bestanden
                Element bestanden = document.createElement("bestanden");
                bestanden.appendChild(document.createTextNode(String.valueOf(m.isBestanden())));
                modul.appendChild(bestanden);

                // lehrveranstaltungen element
                Element lehrveranstaltungen = document.createElement("lehrveranstaltungen");
                modul.appendChild(lehrveranstaltungen);

                for(int x = 0; x < m.getLehrveranstaltungenGesamt().size(); x++){
                    Element lehrveranstaltung = document.createElement("lehrveranstaltung");
                    lehrveranstaltungen.appendChild(lehrveranstaltung);

                    Element workloadInCp = document.createElement("workloadInCP");
                    workloadInCp.appendChild(document.createTextNode(String.valueOf(m.getLehrveranstaltungenGesamt().get(x).getWorkloadInCP())));
                    lehrveranstaltung.appendChild(workloadInCp);

                    Element lehrveranstaltungsTyp = document.createElement("veranstaltungsTyp");
                    lehrveranstaltungsTyp.appendChild(document.createTextNode(String.valueOf(m.getLehrveranstaltungenGesamt().get(x).getVeranstaltungsTyp())));
                    lehrveranstaltung.appendChild(lehrveranstaltungsTyp);
                    
                    Element bestandenLehr = document.createElement("bestanden");
                    bestandenLehr.appendChild(document.createTextNode(String.valueOf(m.getLehrveranstaltungenGesamt().get(x).isBestanden())));
                    lehrveranstaltung.appendChild(bestandenLehr);
                }
            }   

            // create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(xmlFilePath));

            transformer.transform(domSource, streamResult);

            System.out.println("Done creating XML File"); // TODO: Message auf der View


        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

        
    }

}
