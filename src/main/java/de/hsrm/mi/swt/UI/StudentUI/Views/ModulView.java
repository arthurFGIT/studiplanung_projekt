package de.hsrm.mi.swt.UI.StudentUI.Views;


import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Modul;
import de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung.StudienplanService;
import de.hsrm.mi.swt.main.App;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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

	
	public ModulView(Modul m, App app){
		super();
		this.modul = m;
		this.app = app;
		this.studienplanService = app.getStudienplanService();
		this.getStylesheets().add("style.css");
		this.getStyleClass().add("modul-view");
		m.addPropertyChangeListener(this);
		System.out.println("ModulView");
        prefWidth(MODULBREITE);
        prefHeight(MODULHOEHE);
		name = new Text(m.getName());
		cpGesamt = new Text("CP: " + String.valueOf(m.getCpGesamt()));
		
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

	private void createAndAddLehrveranstaltungsViews() {
		for(int i = 0; i < modul.getLehrveranstaltungenGesamt().size(); i++){
			LehrveranstaltungsView lehrveranstaltungsView = new LehrveranstaltungsView(modul.getLehrveranstaltungenGesamt().get(i), modul, app);
			this.getChildren().add(lehrveranstaltungsView);
		}

	}

	private void initialize() {
		
		checkBestanden.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

			if(checkBestanden.isSelected()){
				modul.setBestanden(true);
				this.setStyle("-fx-background-color: #c6f1e5;");			
				System.out.println("Act CP: " + studienplanService.calcActCP().getValue());
				System.out.println("Modul Bestanden: " + modul.isBestanden());
			}
			else{
				modul.setBestanden(false);
				this.setStyle("-fx-background-color: #b2c0f6;");
				studienplanService.calcActCP();
				System.out.println("Modul Bestanden: " + modul.isBestanden());
			}            
		});


	}

	public Modul getModul() {
		return this.modul;
	}

	public void setModul(Modul modul) {
		this.modul = modul;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		switch (evt.getPropertyName()){
			case "falschVerschoben":
			
				if((boolean) evt.getNewValue() == false){
					
					this.setStyle("-fx-background-color : #b2c0f6;");
				} else {
					System.out.println("properyFalse");
					this.setStyle("-fx-background-color : red;");
				}

		}
		
	}
    
}






