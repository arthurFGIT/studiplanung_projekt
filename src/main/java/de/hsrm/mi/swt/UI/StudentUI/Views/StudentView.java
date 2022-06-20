package de.hsrm.mi.swt.UI.StudentUI.Views;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Kompetenz;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Modul;
import de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung.StudienPlan;
import de.hsrm.mi.swt.main.App;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;

public class StudentView extends BorderPane implements PropertyChangeListener{

    private StudienplanungView studienplanungView;
    private UebersichtView uebersichtView;
    private ModulView modulView;

    private Button btnTest;

    private Modul modul; //TODO Test löschen
    private Map<Integer, Modul> modulMap;

    private StudienPlan studienPlan;


    public StudentView(App app){

		Kompetenz kompetenz = new Kompetenz("Test");
		ArrayList<Kompetenz> kompetenzListe = new ArrayList<>();
		kompetenzListe.add(kompetenz);
        modul = new Modul(1, "Prog", "tolles Modul", 5, kompetenzListe, 2, 100, 100);
        studienplanungView = new StudienplanungView();
        uebersichtView = new UebersichtView();
        modulMap = new HashMap<>();
        modulMap.put(2, modul);
        studienPlan = new StudienPlan(modulMap);
        studienPlan.addPropertyChangeListener(this);
        modulView = new ModulView(modul);
        


        btnTest = new Button("Test");

        this.setLeft(modulView);
        this.setCenter(btnTest);

    }
    
    @FXML
	ScrollPane spielfeldui;
	@FXML
	Rectangle modulV;

	public final String MODUL = "modul";

	// @FXML
	// protected void handleKnopfRechts(ActionEvent event) {
	// 	// alle Kringel nach rechts schieben - UI folgt automatisch
	// 	for (Modul k : studienPlan) {
	// 		k.setX(k.getX() + 5);
	// 	}
	// }

	// @FXML
	// protected void handleKnopfLinks(ActionEvent event) {
	// 	// alle Kringel nach links schieben - UI folgt automatisch
	// 	for (Kringel k : spielfeld) {
	// 		k.setX(k.getX() - 5);
	// 	}
	// }

	@FXML
	protected void modulDragDetected(Event event) {
		Dragboard dragboard = modulV.startDragAndDrop(TransferMode.COPY);
		ClipboardContent content = new ClipboardContent();
		content.putString(MODUL);
		dragboard.setContent(content);
		event.consume();
	}

	@FXML
	protected void spielfeldOnDragOver(DragEvent event) {
		if (event.getGestureSource() != studienPlan
				&& (event.getDragboard().hasContent(ModulView.MODULFORMAT) || event.getDragboard().hasString())) {
			event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		}
		event.consume();

	}

	// public int rasterRunden(double x) {
	// 	int rasterbreite = (int) kringel.getRadius() * 2;
	// 	return (int) x / rasterbreite * rasterbreite;
	// }

	@FXML
	protected void spielfeldOnDragDropped(DragEvent event) {
		Dragboard dragboard = event.getDragboard();
		boolean success = false;
		if (dragboard.hasContent(ModulView.MODULFORMAT)) {
			modul = (Modul) dragboard.getContent(ModulView.MODULFORMAT);
			System.out.println("SpielfeldUI.spielfeldOnDragDropped - kringel " + modul);
			int x = (int) event.getX();
			int y = (int) event.getY();
			// Domänenobjekt ändern - UI folgt automatisch
			modul.setxKoordinate(x);
			modul.setyKoordinate(y);
			System.out.println("SpielfeldUI.spielfeldOnDragDropped -> moved kringel " + modul);
			success = true;
		} else if (dragboard.hasString()) {
			String name = dragboard.getString();
			double x = event.getX();
			double y = event.getY();
			System.out.println(String.format("SpielfeldUI.spielfeldOnDragDropped String (new Kringel) %s (%f,%f) %s",
					name, x, y, event.toString()));

			// Domänenobjekte (neuer Kringel zu Spielfeld hinzufügen) - UI folgt automatisch
			// Modul neu = new Modul();
			// studienPlan.add(neu);

			success = true;
		}
		event.setDropCompleted(success);
		event.consume();
	}


	@Override
	public void propertyChange(PropertyChangeEvent event) {
		// UI bei Änderung des zugehörigen Domänenobjekts (Spielfeld) aktualisieren
		System.out.println("SpielfeldUI.update " + event);
		if (event.getPropertyName().equals(StudienPlan.ADD_EVENT)) {
			Modul b = (Modul) event.getNewValue();
			ModulView neuesModul = new ModulView(b);
			AnchorPane ap = (AnchorPane) spielfeldui.getContent();
			ap.getChildren().add(neuesModul);
		} else {
			throw new IllegalArgumentException("UnbehandeltesEvent " + event);
		}
	}

}
