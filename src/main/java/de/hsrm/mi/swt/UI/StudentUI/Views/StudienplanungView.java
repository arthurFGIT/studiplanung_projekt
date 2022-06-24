package de.hsrm.mi.swt.UI.StudentUI.Views;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Modul;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.ModulService;
import de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung.StudienPlan;
import de.hsrm.mi.swt.main.App;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class StudienplanungView extends ScrollPane implements PropertyChangeListener {

	public final String MODUL = "modul";

	private Modul modul; // TODO Test löschen
	private StudienPlan studienPlan;
	private Map<Integer, Modul> modulMap;
	private ModulView modulView;
	private Pane container;
	private Map<Integer, ModulView> modulViewListe;

	private FlowPane pane0, pane1, pane2, pane3, pane4, pane5, pane6, pane7;

	private Map<Integer, FlowPane> flowPaneListe;
	private VBox containerVBox;

	// private Rectangle modul;

	private ModulService modulService; // später durch Studienplanservice -> lade Plan ersetzen

	public StudienplanungView(App app) {

		setPrefSize(450, 600);
		modulService = app.getModulService();
		modulMap = modulService.getModulMap();
		studienPlan = new StudienPlan(modulMap);
		studienPlan.addPropertyChangeListener(this);
		System.out.println("Studienplan added observer");
		System.out.println(modulMap.toString());
		modul = modulMap.get(0);
		System.out.println(modul.toString());
		container = new Pane();
		container.setMinHeight(800);
		containerVBox = new VBox();


		modulViewListe = new HashMap<>();
		flowPaneListe = new HashMap<>();


		

		pane0 = new FlowPane();
		flowPaneListe.put(0, pane0);
		pane0.setMinWidth(200.0);
		pane0.setMinHeight(50.0);
		pane0.setStyle("-fx-background-color: #AD5947;");
		pane1 = new FlowPane();
		flowPaneListe.put(1, pane1);
		pane1.setMinWidth(200.0);
		pane1.setMinHeight(50.0);
		pane1.setStyle("-fx-background-color: #2B57E1;");
		pane2 = new FlowPane();
		flowPaneListe.put(2, pane2);
		pane3 = new FlowPane();
		flowPaneListe.put(3, pane3);
		pane4 = new FlowPane();
		flowPaneListe.put(4, pane4);
		pane5 = new FlowPane();
		flowPaneListe.put(5, pane5);
		pane6 = new FlowPane();
		flowPaneListe.put(6, pane6);
		pane7 = new FlowPane();
		flowPaneListe.put(7, pane7);

		this.ladePlan();

		containerVBox.getChildren().addAll(pane0,pane1);

		container.getChildren().addAll(containerVBox);

		



		this.setContent(container);

		initialize();

	}

	public void ladePlan(){
		int i = 0;
		for(int k : modulMap.keySet()){
			ModulView modulView = new ModulView(modulMap.get(k));	
			modulViewListe.put(i, modulView);
			i++;
		}

		for(int j = 0; j <= flowPaneListe.size(); j++){
			fuegeModulViewsInFlowPanes("pane"+j, j);
		}

		
	}

	public void fuegeModulViewsInFlowPanes(String key, int j){
		for(int k : modulViewListe.keySet()){
			System.out.println(modulViewListe.get(k));

			if(modulViewListe.get(k).getModul().getVerschobenesFachsemester().getid()-1 == j){
				flowPaneListe.get(j).getChildren().add(modulViewListe.get(k));
			}
		}

	}


	public void initialize() {
		// modulView.setOnDragDetected(new EventHandler<MouseEvent>() {
		// 	@Override
		// 	public void handle(MouseEvent event) {
		// 		System.out.println("Drag detected");
		// 		Dragboard dragboard = modulView.startDragAndDrop(TransferMode.COPY);
		// 		ClipboardContent content = new ClipboardContent();
		// 		content.putString(MODUL);
		// 		dragboard.setContent(content);
		// 		event.consume();
		// 	}

		// });

		// this.setOnDragOver(new EventHandler<DragEvent>() {
		// 	@Override
		// 	public void handle(DragEvent event) {
		// 		System.out.println("Search Drop Place");
		// 		if (event.getDragboard().hasString()) {
		// 			event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		// 		}
		// 		event.consume();
		// 	}
		// });

		// this.setOnDragDropped(new EventHandler<DragEvent>() {
		// 	@Override
		// 	public void handle(DragEvent event) {
		// 		Dragboard dragboard = event.getDragboard();
		// 		boolean success = false;
		// 		if(dragboard.hasString()){
		// 			// modul = (Modul) dragboard.getContent(ModulView.MODULFORMAT);
		// 			int x = (int) event.getX();
		// 			int y = (int) event.getY();
		// 			modul.setxKoordinate(x);
		// 			modul.setyKoordinate(y);
		// 			System.out.println("StudienplanungView.thisOnDragDropped -> moved Modul " + modul);
		// 			success = true;

		// 		}
				
		// 		event.setDropCompleted(success);
		// 		event.consume();
		// 	}
		// });


	}
	
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		System.out.println("HALLOOOOO");
		modul = (Modul) event.getNewValue();
		modulView = new ModulView(modul);
		this.getContent();
		this.getChildren().add(modulView);
	}

}
