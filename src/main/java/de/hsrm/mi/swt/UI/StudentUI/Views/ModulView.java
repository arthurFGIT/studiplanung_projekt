package de.hsrm.mi.swt.UI.StudentUI.Views;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Modul;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.ModulService;
import de.hsrm.mi.swt.main.App;
import javafx.event.EventHandler;
import javafx.scene.input.DataFormat;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class ModulView extends StackPane{

    // //TODO: verstehen was das macht
	// public static final DataFormat MODULFORMAT = new DataFormat("application.Modul");
	public static final double MODULBREITE = 100.0;
	public static final double MODULHOEHE = 50.0;
	private Modul modul;
	private Text name;
	private Text cpGesamt;
	private App app;

	
	public ModulView(Modul m, App app){
		super();
		this.modul = m;
		this.app = app;
		// modul.addPropertyChangeListener(this);

		System.out.println("ModulView");
        prefWidth(MODULBREITE);
        prefHeight(MODULHOEHE);
		setStyle("-fx-background-color: green");
		name = new Text(m.getName());
		cpGesamt = new Text("CP: " + String.valueOf(m.getCpGesamt()));
		
		this.getChildren().addAll(name);	

		// setOnMouseDragged(new EventHandler<MouseEvent>() {
		// 	public void handle(MouseEvent event) {
		// 		event.consume();
		// 	}
		// });

		// setOnMouseReleased(new EventHandler<MouseEvent>() {
		// 	public void handle(MouseEvent event) {
		// 		// System.out.println("KringelUI - onMouseReleased " + event);
		// 		System.out.println(event.getSource());
		// 		FlowPaneView flowPane = (FlowPaneView) event.getSource();
		// 		flowPane.getSemester();
				
		// 		event.consume();
		// 	}
		// });



	}

	public Modul getModul() {
		return this.modul;
	}

	public void setModul(Modul modul) {
		this.modul = modul;
	}

	// @Override
	// public void propertyChange(PropertyChangeEvent event) {
	// 	// UI bei Änderung des zugehörigen Domänenobjekts (Kringel) aktualisieren
	// 	System.out.println("ModulUI - update " + event);
	// 	modul = (Modul) event.getSource();
	// 	System.out.println("MODUL PropertyChange: " + modul);
	// 	switch (event.getPropertyName()) {
	// 		case Modul.SET_VERSCH_SEMESTER:
				
				
	// 			break;
	// 		default:
	// 			throw new IllegalArgumentException("UnbehandeltesEvent " + event);
	// 	}				
	// }


    
}






