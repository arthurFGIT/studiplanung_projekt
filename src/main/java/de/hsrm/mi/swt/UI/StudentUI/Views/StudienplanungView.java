package de.hsrm.mi.swt.UI.StudentUI.Views;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.AngebotsIntervall;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Fachsemester;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Kompetenz;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Modul;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.ModulService;
import de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung.StudienPlan;
import de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung.StudienplanService;
import de.hsrm.mi.swt.main.App;
import javafx.event.Event;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.shape.Rectangle;

public class StudienplanungView extends ScrollPane implements PropertyChangeListener{

    public final String MODUL = "modul";

    private Modul modul; //TODO Test löschen
    private StudienPlan studienPlan;
    private Map<Integer, Modul> modulMap;
    private ModulView modulView;
    
    //private Rectangle modul;
    

    private ModulService modulService; //später durch Studienplanservice -> lade Plan ersetzen


    public StudienplanungView(App app){

        // Kompetenz kompetenz = new Kompetenz("Test");
		// ArrayList<Kompetenz> kompetenzListe = new ArrayList<>();
		// kompetenzListe.add(kompetenz);
		// Fachsemester fachsemester = new Fachsemester(5, AngebotsIntervall.WISO);
        // modul = new Modul(1, "Prog", "tolles Modul", 5, kompetenzListe, fachsemester, fachsemester, true, 200, 200); //TODO: rausmachen
        
        // modulMap = new HashMap<>();
        // modulMap.put(2, modul);
        modulService = app.getModulService();
        modulMap = modulService.getModulMap();
        studienPlan = new StudienPlan(modulMap);
        studienPlan.addPropertyChangeListener(this);
        System.out.println("Studienplan added observer");
        System.out.println(modulMap.toString());
        modul = modulMap.get(1);
        System.out.println(modul.toString());
        modulView = new ModulView(modul);

        this.setContent(modulView);
		
		// modulV.getOnDragOver();
    }

    

    protected void modulDragDetected(Event event) {
        System.out.println("Drag detected");
		Dragboard dragboard = modulView.startDragAndDrop(TransferMode.COPY);
		ClipboardContent content = new ClipboardContent();
		content.putString(MODUL);
		dragboard.setContent(content);
		event.consume();
	}


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

	// @FXML
	

	// @FXML
	protected void studentUIOnDragOver(DragEvent event) {
		if (event.getGestureSource() != studienPlan
				&& (event.getDragboard().hasContent(ModulView.MODULFORMAT) || event.getDragboard().hasString())) {
			event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		}
		event.consume();

	}

	// public int rasterRunden(double x) {

	// 	int rasterbreite = (int) modulV.getWidth() * 2;
	// 	return (int) x / rasterbreite * rasterbreite;
	// }

	// @FXML
	// protected void spielfeldOnDragDropped(DragEvent event) {
	// 	System.out.println("Test gfa fds ");
	// 	Dragboard dragboard = event.getDragboard();
	// 	boolean success = false;
		
	// 	// TODO: Abfrage validierung
	// 	modul = (Modul) dragboard.getContent(ModulView.MODULFORMAT);
	// 	System.out.println("SpielfeldUI.spielfeldOnDragDropped - kringel " + modul);
	// 	int x = (int) event.getX();
	// 	int y = (int) event.getY();
	// 	System.out.println(x + " " + y);
	// 	// Domänenobjekt ändern - UI folgt automatisch
	// 	modul.setxKoordinate(x);
	// 	modul.setyKoordinate(y);
	// 	System.out.println("SpielfeldUI.spielfeldOnDragDropped -> moved Modul " + modul);
	// 	success = true;


	// 	event.setDropCompleted(success);
	// 	event.consume();
	// }


	@Override
	public void propertyChange(PropertyChangeEvent event) {
		// UI bei Änderung des zugehörigen Domänenobjekts (Spielfeld) aktualisieren
		// System.out.println("SpielfeldUI.update " + event);
		// if (event.getPropertyName().equals(StudienPlan.ADD_EVENT)) {
		// 	Modul b = (Modul) event.getNewValue();
		// 	ModulView neuesModul = new ModulView(b);
		// 	AnchorPane ap = (AnchorPane) spielfeldui.getContent();
		// 	ap.getChildren().add(neuesModul);
		// } else {
		// 	throw new IllegalArgumentException("UnbehandeltesEvent " + event);
		// }
	}

    
}
