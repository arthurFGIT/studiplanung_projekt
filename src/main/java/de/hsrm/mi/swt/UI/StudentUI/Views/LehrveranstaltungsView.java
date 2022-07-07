package de.hsrm.mi.swt.UI.StudentUI.Views;

import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.AngebotsIntervall;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Lehrveranstaltung;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Modul;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.VeranstaltungsTyp;
import de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung.StudienplanService;
import de.hsrm.mi.swt.main.App;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
/**
 * LehrveranstaltungsView, stellt die Lehrveranstaltung in der ModulView dar
 * @author Marie Bohnert, Beate Arnold, Arthur Fieguth
 */
public class LehrveranstaltungsView extends VBox{

	public static final double MODULBREITE = 80.0;
	public static final double MODULHOEHE = 40.0;
	private Lehrveranstaltung lehrveranstaltung;
	private Text name;
	private Text cpGesamt;
	private App app;
	private CheckBox checkBestanden;
	private HBox nameWithCheck;
	private StudienplanService studienplanService;
	private Modul modul;
	
	/**
	 * Konstruktor für die LehrveranstaltungsView
	 * @param l : bekommt eine Lehrveranstaltung mit
	 * @param m : bekommt das Modul, der jeweiligen Lehrveranstalung mit
	 * @param app
	 */
	public LehrveranstaltungsView(Lehrveranstaltung l, Modul m, App app){
		this.lehrveranstaltung = l;
		this.app = app;
		this.studienplanService = app.getStudienplanService();
		this.getStylesheets().add("style.css");
		this.getStyleClass().add("lehrveranstaltungs-view");
		this.modul = m;

        prefWidth(MODULBREITE);
        prefHeight(MODULHOEHE);
		name = new Text(String.valueOf(lehrveranstaltung.getVeranstaltungsTyp()));
        cpGesamt = new Text("CP: " + String.valueOf(lehrveranstaltung.getWorkloadInCP()));
		
		checkBestanden = new CheckBox();
		if(lehrveranstaltung.isBestanden()){
			checkBestanden.setSelected(true);
			this.setStyle("-fx-background-color: #c6f1e5;");	
		}

		nameWithCheck = new HBox(name, checkBestanden);
				
		this.getChildren().addAll(nameWithCheck);	

		initialize();
	}

	/**
	 * Initialisiert Click Handler für die CheckBox, wenn eine Lehrveranstaltung bestanden ist
	 */
	private void initialize() {
		
		checkBestanden.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

			if(checkBestanden.isSelected()){
				lehrveranstaltung.setBestanden(true);
				this.setStyle("-fx-background-color: #c6f1e5;");		
				if(lehrveranstaltung.getVeranstaltungsTyp() == VeranstaltungsTyp.PRAKTIKUM){
					modul.getVerschobenesFachsemester().setAngebotsIntervall(AngebotsIntervall.WISO);
					System.out.println("verschobenes Fachsemester Angebotsintervall zu WISO gesetzt");
				}	
				System.out.println("Act CP: " + studienplanService.calcActCP().getValue());
				System.out.println("Lehrveranstaltung Bestanden: " + lehrveranstaltung.isBestanden());
			}
			else{
				lehrveranstaltung.setBestanden(false);
				this.setStyle("-fx-background-color: #b2c0f6;");
				studienplanService.calcActCP();
				if(lehrveranstaltung.getVeranstaltungsTyp() == VeranstaltungsTyp.PRAKTIKUM){
					modul.getVerschobenesFachsemester().setAngebotsIntervall(modul.getVorherigesFachsemester().getAngebotsIntervall());
					System.out.println("verschobenes Fachsemester Angebotsintervall zurueck gesetzt");
				}	
				System.out.println("Lehrveranstaltung Bestanden: " + lehrveranstaltung.isBestanden());
			}            
		});

	}

    
	/** 
	 * Gibt die Lehrveranstaltung zurück
	 * @return Lehrveranstaltung
	 */
	public Lehrveranstaltung getLehrveranstaltung() {
        return lehrveranstaltung;
    }

	public CheckBox getCheckBestanden() {
		return checkBestanden;
	}

	public void setCheckBestanden(CheckBox checkBestanden) {
		this.checkBestanden = checkBestanden;
	}   
	 
}
    




