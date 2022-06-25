package de.hsrm.mi.swt.UI.StudentUI.Views;

import java.beans.PropertyChangeListener;

import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Modul;
import javafx.event.EventHandler;
import javafx.scene.input.DataFormat;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class ModulView extends StackPane{

    //TODO: verstehen was das macht
	public static final DataFormat MODULFORMAT = new DataFormat("application.Modul");
	public static final double MODULBREITE = 100.0;
	public static final double MODULHOEHE = 50.0;
	private Modul modul;
	private Text name;
	private Text cpGesamt;

	public ModulView(Modul m) {
		super();
		this.modul = m;
		// Kringel-UI soll Änderungen des Kringel-Domänenobjekts beobachten
		// modul.addPropertyChangeListener(this);

		System.out.println("ModulView");
        prefWidth(MODULBREITE);
        prefHeight(MODULHOEHE);
		setStyle("-fx-background-color: green");
		name = new Text(m.getName());
		cpGesamt = new Text("CP: " + String.valueOf(m.getCpGesamt()));
		
		this.getChildren().addAll(name, cpGesamt);	

		setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				event.consume();
			}
		});



	}

	public Modul getModul() {
		return this.modul;
	}

	public void setModul(Modul modul) {
		this.modul = modul;
	}


    
}






