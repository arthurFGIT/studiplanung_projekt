package de.hsrm.mi.swt.UI.StudentUI.Views;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.AngebotsIntervall;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Fachsemester;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Modul;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.ModulService;
import de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung.Studienplan;
import de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung.StudienplanService;
import de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung.Studiensemester;
import de.hsrm.mi.swt.main.App;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class StudienplanungView extends ScrollPane {

	public final String MODUL = "modul";

	private Modul modul; // TODO Test löschen
	private Map<Integer, Modul> modulMap;
	private ModulView modulView;
	private Pane container;
	private Map<Integer, ModulView> modulViewMap;
	private Map<Integer, Map<Integer, ModulView>> modulViewsListe;


	private Map<Integer, FlowPaneView> flowPaneMap;
	private VBox containerVBox;

	private Fachsemester aktuellesFachsemester, vorherigesFachsemester;

	private App app;

	// private Rectangle modul;

	private ModulService modulService; // später durch Studienplanservice -> lade Plan ersetzen
	private StudienplanService studienplanService;


	public StudienplanungView(App app) {
		this.app = app;

		setPrefSize(450, 600);
		modulService = app.getModulService();
		studienplanService = app.getStudienplanService();
		modulMap = modulService.getModulMap();
		System.out.println("Studienplan added observer");
		System.out.println(modulMap.toString());
		// modul = modulMap.get(0);
		// System.out.println(modul.toString());
		container = new Pane();
		container.setMinHeight(800);
		containerVBox = new VBox();


		modulViewsListe = new HashMap<>();
		flowPaneMap = new HashMap<>();

		// for(int k : modulMap.keySet()){
		// 	modulMap.get(k).addPropertyChangeListener(this);
		// }

		// for(int k : flowPaneMap.keySet()){
		// 	flowPaneMap.get(k).addPropertyChangeListener(this);
		// }



		this.ladePlan();

		// for(int k : modulViewsListe.keySet()){
		// 	((Modul) modulViewsListe.get(k)).addPropertyChangeListener(this);
		// }

		for(int k : flowPaneMap.keySet()){
			containerVBox.getChildren().add(flowPaneMap.get(k));
		}

		container.getChildren().addAll(containerVBox);


		this.setContent(container);

		initialize();

	}

	public void ladePlan(){	

		// erstelle ModulViewMaps und adde sie der modulViewsListe
		for(int y = 1; y <= studienplanService.maxSemesterAnzahl(); y++){
			modulViewMap = new HashMap<>();
			modulViewsListe.put(y, modulViewMap);
		}

		
		// ModulViews für jedes Modul erstellen und der jeweiligen liste adden
		for(int k : modulMap.keySet()){
			ModulView modulView = new ModulView(modulMap.get(k), app);	
			modulViewsListe.get(modulMap.get(k).getVerschobenesFachsemester().getid()).put(k+1, modulView);
		}

		for(int x = 1; x <= studienplanService.maxSemesterAnzahl(); x++){
			Fachsemester fachsemester;
			if(x%2 == 0){
				fachsemester = new Fachsemester(x, AngebotsIntervall.SOMMER);		
			}
			else{
				fachsemester = new Fachsemester(x, AngebotsIntervall.WINTER);			
			}

			FlowPaneView paneView = new FlowPaneView(app, fachsemester, modulViewsListe.get(x), modulViewsListe, modulService.getStudienplan().getSemesterMap().get(x));

			flowPaneMap.put(x, paneView);
		}


		
		

		// for(int j = 0; j <= flowPaneMap.size(); j++){
		// 	fuegeModulViewsInFlowPanes("pane"+j, j);
		// }

		
	}

	// public void fuegeModulViewsInFlowPanes(String key, int j){
	// 	for(int k : modulViewMap.keySet()){
	// 		System.out.println(modulViewMap.get(k));

	// 		if(modulViewMap.get(k).getModul().getVerschobenesFachsemester().getid()-1 == j){
	// 			flowPaneMap.get(j).getChildren().add(modulViewMap.get(k));
	// 		}
	// 	}

	// }


	public void initialize() {

		for(int k : flowPaneMap.keySet()){
            flowPaneMap.get(k).setOnDragOver(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent event) {
                    System.out.println("Search Drop Place");
                    if (event.getDragboard().hasString()) {
                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                    }
                    event.consume();
                }
            });
    
            flowPaneMap.get(k).setOnDragDropped(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent event) {
					
                    Dragboard dragboard = event.getDragboard();
                    boolean success = false;

                    if(dragboard.hasString()){
						
						System.out.println(dragboard.getString());

						modul = modulMap.get(Integer.parseInt(dragboard.getString()));

						vorherigesFachsemester = modul.getVerschobenesFachsemester();
						modul.setVorherigesFachsemester(vorherigesFachsemester);
						aktuellesFachsemester = flowPaneMap.get(k).getSemester();
                        modul.setVerschobenesFachsemester(aktuellesFachsemester);


						modulService.getStudienplan().holeStudiensemesterMitId(modul.getVerschobenesFachsemester().getid()).addToSemester(modul);
						modulService.getStudienplan().holeStudiensemesterMitId(modul.getVorherigesFachsemester().getid()).removeFromSemester(modul);

						System.out.println("DROP HAT GEKLAPPT");

                        success = true;
    
                    }
                    
                    event.setDropCompleted(success);
                    event.consume();
                }
            });
        }
	}

	
	// @Override
	// public void propertyChange(PropertyChangeEvent event) {
	// 	// UI bei Änderung des zugehörigen Domänenobjekts (Kringel) aktualisieren
	// 	System.out.println("ModulUI - update " + event);
	// 	modul = (Modul) event.getSource();
	// 	System.out.println("MODUL PropertyChange: " + modul);
		
		
	// 	int semester = modul.getVerschobenesFachsemester().getid();
	// 	ModulView modulViewTemp = flowPaneMap.get(vorherigesFachsemester.getid()).getModulViewMap().get(modul.getId());

	// 	switch (event.getPropertyName()) {
	// 		case Modul.SET_VERSCH_SEMESTER:

				



	// 			for(int o : flowPaneMap.keySet()){
	// 				for(int x : flowPaneMap.get(o).getModulViewMap().keySet()){
	// 					System.out.println(flowPaneMap.get(o).getModulViewMap().get(x));
	// 				}

	// 				System.out.println("-----------------------------------");
	// 			}

	// 			break;
	// 		case Studiensemester.ADD_MODUL_TO_SEMESTER:
	// 			flowPaneMap.get(semester).getModulViewMap().put(modulViewMap.size(), modulViewTemp);
			
			
	// 		case Studiensemester.REMOVE_MODUL_FROM_SEMESTER:
	// 			for(int k : flowPaneMap.keySet()){
	// 				if(k == vorherigesFachsemester.getid()){
	// 					for(int x : flowPaneMap.get(k).getModulViewMap().keySet()){
	// 						if(flowPaneMap.get(k).getModulViewMap().get(x).getModul().getId() == modul.getId()){
	// 							modulViewTemp = flowPaneMap.get(k).getModulViewMap().get(x);
	// 							flowPaneMap.get(k).getModulViewMap().remove(x);
	// 							break;
	// 						}
	// 					}
	// 				}
	// 			}
				
	// 		default:
	// 			throw new IllegalArgumentException("UnbehandeltesEvent " + event);
	// 	}
	// }
	
	// @Override
	// public void propertyChange(PropertyChangeEvent event) {
	// 	System.out.println("HALLOOOOO");
	// 	modul = (Modul) event.getNewValue();
	// 	modulView = new ModulView(modul);
	// 	this.getContent();
	// 	this.getChildren().add(modulView);
	// }

}
