package de.hsrm.mi.swt.UI.StudentUI.Views;

import java.util.HashMap;
import java.util.Map;

import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.AngebotsIntervall;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Fachsemester;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Modul;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.ModulService;
import de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung.StudienplanService;
import de.hsrm.mi.swt.main.App;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class StudienplanungView extends ScrollPane {

	private Modul modul;
	private Map<Integer, Modul> modulMap;
	private Pane container;
	private Map<Integer, ModulView> modulViewMap;
	private Map<Integer, Map<Integer, ModulView>> modulViewsListe;


	private Map<Integer, FlowPaneView> flowPaneMap;
	private VBox containerVBox;

	private Fachsemester aktuellesFachsemester, vorherigesFachsemester;

	private App app;


	private ModulService modulService; // später durch Studienplanservice -> lade Plan ersetzen
	private StudienplanService studienplanService;


	public StudienplanungView(App app) {
		this.app = app;

		setPrefSize(1000, 800);
		modulService = app.getModulService();
		studienplanService = app.getStudienplanService();
		modulMap = modulService.getModulMap();
		System.out.println("Studienplan added observer");
		container = new Pane();
		container.setMinHeight(800);
		containerVBox = new VBox();


		modulViewsListe = new HashMap<>();
		flowPaneMap = new HashMap<>();

		this.ladePlan();

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
			paneView.getStyleClass().add("flow-pane");
			flowPaneMap.put(x, paneView);
		}		
	}

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
}
