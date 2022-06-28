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



public class StudienplanService {

    private ModulService modulService;
    private CheckService checkService;
    private ErrorService errorService;
    private Map<Integer, Modul> modulMap;
    private int maxSemesterAnzahl;

    public static final String xmlFilePath = "moduleIndividual.xml";

    


    public StudienplanService(ModulService modulService, CheckService checkService, ErrorService errorService) {
        this.modulService = modulService;
        this.checkService = checkService;
        this.errorService = errorService;
        modulMap = modulService.getModulMap();
        maxSemesterAnzahl = 0;
    }

    public int maxSemesterAnzahl(){
        maxSemesterAnzahl = modulMap.get(0).getVerschobenesFachsemester().getid();
        for(int k : modulMap.keySet()){
            if(modulMap.get(k).getVerschobenesFachsemester().getid() > maxSemesterAnzahl){
                maxSemesterAnzahl = modulMap.get(k).getVerschobenesFachsemester().getid();
            }
        }
        return maxSemesterAnzahl;
    }

    public void verschiebeModul(int id, int x, int y){} //TODO ID oder ganzes Modul mitgeben

        
    //     boolean fortschritt = checkService.checkFortschrittsregel(modulService.holeModulmitId(id), zielSemester);
    //     if(fortschritt){
    //         boolean kompetenz = checkService.checkKompetenzen(modul, zielSemester);
    //         if(kompetenz){
    //             boolean semster = checkService.checkSemester(angebotsIntervall, neueFachsemester);
    //             if(semster){
    //                 Modul modul = modulService.holeModulmitId(id);
    //                 modul.setxKoordinate(x);
    //                 modul.setyKoordinate(y);
    //             }
    //             else{
    //                 errorService.getErrorMessages().add("Das Modul wird in diesem Semester nicht angeboten.");
    //             }

    //         }
    //         else{ //TODO: evtl anders gestalten, da Kompetenzen nur Empfehlung 
    //             errorService.getErrorMessages().add("Du benötigst Kompetenzen aus vorherigen Modulen, die du noch nicht hast.");
    //         }

    //     }
    //     else{
    //         errorService.getErrorMessages().add("Bitte beachte die Fortschrittsregelung.");
    //     }
        
        
        
    // }


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
