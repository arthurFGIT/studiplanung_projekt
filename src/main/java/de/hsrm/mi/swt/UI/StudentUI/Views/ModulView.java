package de.hsrm.mi.swt.UI.StudentUI.Views;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Modul;
import javafx.event.EventHandler;
import javafx.scene.input.DataFormat;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ModulView extends Rectangle implements PropertyChangeListener {

    //TODO: verstehen was das macht
	public static final DataFormat MODULFORMAT = new DataFormat("application.Modul");
	public static final int MODULBREITE = 100;
	public static final int MODULHOEHE = 50;
	private Modul modul;

	public ModulView(Modul m) {
		super();
		this.modul = m;
		// Kringel-UI soll Änderungen des Kringel-Domänenobjekts beobachten
		modul.addPropertyChangeListener(this);

		System.out.println("ModulView");
		setFill(Color.GREENYELLOW);
        setX(modul.getxKoordinate());
        setY(modul.getyKoordinate());
        setWidth(MODULBREITE);
        setHeight(MODULHOEHE);
        // setArcWidth(20);
        // setArcHeight(20);
	

		setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				System.out.println("KringelUI - onMouseDragged " + event);
				setX(event.getX());
				setY(event.getY());
				event.consume();
			}
		});

		setOnMouseReleased(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				// System.out.println("KringelUI - onMouseReleased " + event);
				System.out.println((int) event.getX() + " " + (int) event.getY());
				modul.setxKoordinate((int) event.getX());
				modul.setyKoordinate((int) event.getY());
				event.consume();
			}
		});

	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		// UI bei Änderung des zugehörigen Domänenobjekts (Kringel) aktualisieren
		System.out.println("ModulUI - update " + event);
		Modul modul = (Modul) event.getSource();
		switch (event.getPropertyName()) {
			case Modul.SET_X:
				setX(modul.getxKoordinate());
				break;
			case Modul.SET_Y:
				setY(modul.getyKoordinate());
				break;
			default:
				throw new IllegalArgumentException("UnbehandeltesEvent " + event);
		}
	}


    
}






