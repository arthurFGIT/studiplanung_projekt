package de.hsrm.mi.swt.UI.StudentUI.Views;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Modul;
import de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung.StudienplanService;
import de.hsrm.mi.swt.main.App;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
/**
 * ModulView, die ein einzelnes Modul darstellt
 * @author Marie Bohnert, Beate Arnold, Arthur Fieguth
 */
public class ModulView extends VBox implements PropertyChangeListener{

	public static final double MODULBREITE = 100.0;
	public static final double MODULHOEHE = 50.0;
	private Modul modul;
	private Text name;
	private Text cpGesamt;
	private App app;
	private CheckBox checkBestanden;
	private HBox nameWithCheck;
	private StudienplanService studienplanService;
	private List<LehrveranstaltungsView> lehrveranstaltungenViews;

	
	/**
	 * Konstruktor für die ModulView
	 * @param m : bekommt das jeweilige Modul mitgegeben
	 * @param app
	 */
	public ModulView(Modul m, App app){
		this.modul = m;
		this.app = app;
		this.studienplanService = app.getStudienplanService();
		this.getStylesheets().add("style.css");
		this.getStyleClass().add("modul-view");
		m.addPropertyChangeListener(this);
        prefWidth(MODULBREITE);
        prefHeight(MODULHOEHE);
		name = new Text(m.getName());
		cpGesamt = new Text("CP: " + String.valueOf(m.getCpGesamt()));
		this.setSpacing(5);

		lehrveranstaltungenViews = new ArrayList();
		
		checkBestanden = new CheckBox();
		if(modul.isBestanden()){
			checkBestanden.setSelected(true);
			this.setStyle("-fx-background-color: #c6f1e5;");	
		}


		nameWithCheck = new HBox(name, checkBestanden);
				
		this.getChildren().addAll(nameWithCheck, cpGesamt);	
		createAndAddLehrveranstaltungsViews();

		initialize();
	}

	/**
	 * Erstellt die zugehörigen LehrveranstaltungsViews und addet sie der ModulView
	 */
	private void createAndAddLehrveranstaltungsViews() {
		for(int i = 0; i < modul.getLehrveranstaltungenGesamt().size(); i++){
			LehrveranstaltungsView lehrveranstaltungsView = new LehrveranstaltungsView(modul.getLehrveranstaltungenGesamt().get(i), modul, app);
			lehrveranstaltungsView.getStyleClass().add("lehrveranstaltung-view");
			lehrveranstaltungenViews.add(lehrveranstaltungsView);
			this.getChildren().add(lehrveranstaltungsView);			
		}
		
	}
	/**
	 * Initialisiert Click Handler für die CheckBox, wenn ein Modul bestanden ist
	 */
	private void initialize() {
		
		checkBestanden.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

			if(checkBestanden.isSelected()){
				modul.setBestanden(true);

				for(int i = 0; i < modul.getLehrveranstaltungenGesamt().size(); i++){
					modul.getLehrveranstaltungenGesamt().get(i).setBestanden(true);
					lehrveranstaltungenViews.get(i).getCheckBestanden().setSelected(true);
					lehrveranstaltungenViews.get(i).setStyle("-fx-background-color: #c6f1e5;");	
				}

				if (modul.getFalschVerschoben()){
					this.setStyle("-fx-background-color: #fbe9cb;");	
				} else {
					this.setStyle("-fx-background-color: #c6f1e5;");	
				}
						
			}
			else{
				modul.setBestanden(false);
				for(int i = 0; i < modul.getLehrveranstaltungenGesamt().size(); i++){
					modul.getLehrveranstaltungenGesamt().get(i).setBestanden(true);
					lehrveranstaltungenViews.get(i).getCheckBestanden().setSelected(true);
				}
				if (modul.getFalschVerschoben()){
					this.setStyle("-fx-background-color: #ffd6d6;");	
				} else {
					this.setStyle("-fx-background-color: #b2c0f6;");	
				}
				
				studienplanService.calcActCP();
			}            
		});


	}

	
	/** 
	 * Gibt das Modul zurück
	 * @return Modul
	 */
	public Modul getModul() {
		return this.modul;
	}
	
	/** 
	 * Property Change Methode, Färbung der ModulViews, wenn etwas falsch verschoben wurde
	 * @param evt
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		switch (evt.getPropertyName()){
			case "falschVerschoben":
			
				if((boolean) evt.getNewValue() == false){
				
					this.setStyle("-fx-background-color : #b2c0f6;");
				} else {
					
					this.setStyle("-fx-background-color : #ffd6d6;");
					
				}

		}
		
	}
    
}






