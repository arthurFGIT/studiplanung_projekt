package de.hsrm.mi.swt.UI.StudentUI.Views;

import java.util.HashMap;
import java.util.Map;

import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.AngebotsIntervall;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Fachsemester;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Modul;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.ModulService;
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
/**
 * StudienplanungView verwaltet alle Flowpaneviews in einer Scrollpane
 * @author Marie Bohnert, Beate Arnold, Arthur Fieguth
 */
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
	private Button addSemester;
	private ModulService modulService;
	private StudienplanService studienplanService;


	/**
	 * Konstruktor der StudienplanungView, der Button und FlowPaneViews läd
	 * @param app
	 */
	public StudienplanungView(App app) {
		this.app = app;
		this.getStylesheets().add("style.css");
		this.getStyleClass().add("body");
		setPrefSize(1400, 800);
		modulService = app.getModulService();
		studienplanService = app.getStudienplanService();
		modulMap = modulService.getModulMap();
		System.out.println("Studienplan added observer");
		container = new Pane();
		container.setMinHeight(800);
		containerVBox = new VBox();
        containerVBox.setSpacing(20.0);

		addSemester = new Button("Semester hinzufuegen");
		addSemester.getStyleClass().add("add-button");


		modulViewsListe = new HashMap<>();
		flowPaneMap = new HashMap<>();

		this.ladePlan(studienplanService.maxSemesterAnzahl());

		this.ladeViews();

		this.setContent(container);
		this.setFitToWidth(true);

		initialize();
		initButton();


	}

	/**
	 * Läd die FlowPaneViews und Button neu, wenn ein neues Semester hinzugefügt wird
	 * @param semesteranzahl : die Anzahl der neuen Semester
	 */
	public void ladeViewsNew(int semesteranzahl){
		containerVBox.getChildren().clear();
		container.getChildren().clear();
		containerVBox.getChildren().add(addSemester);
		ladePlan(semesteranzahl);
		for(int i = flowPaneMap.size(); i > 0 ; i--){
			containerVBox.getChildren().add(flowPaneMap.get(i));
		}
		System.out.println("Neue Views");
		container.getChildren().addAll(containerVBox);
		container.getStyleClass().add("flow-panes");

		this.setContent(container);
		initialize();

	}

	/**
	 * Läd die Views zu beginn mit den Standardsemestern
	 */
	public void ladeViews(){
		containerVBox.getChildren().add(addSemester);
		for(int i = flowPaneMap.size(); i > 0 ; i--){
			containerVBox.getChildren().add(flowPaneMap.get(i));
		}
		container.getChildren().addAll(containerVBox);

		
		container.getStyleClass().add("flow-panes");
	}

	/**
	 * Erstellt die einzelnen FlowPaneViews mit zugehörigen ModulViews
	 * @param semesteranzahl : die Semesteranzahl (neu oder alt)
	 */
	public void ladePlan(int semesteranzahl){	


		// erstelle ModulViewMaps und adde sie der modulViewsListe
		for(int y = 1; y <= studienplanService.maxSemesterAnzahl(); y++){
			modulViewMap = new HashMap<>();
			modulViewsListe.put(y, modulViewMap);
		}

		
		// ModulViews für jedes Modul erstellen und der jeweiligen liste adden
		for(int k : modulMap.keySet()){
			ModulView modulView = new ModulView(modulMap.get(k), app);
			Modul modul = modulMap.get(k);
			if (modul.getFalschVerschoben()){
				if (modul.isBestanden()){
					modulView.setStyle("-fx-background-color : #fbe9cb;");
				} else {
					modulView.setStyle("-fx-background-color : #ffd6d6;");
				}
				
			} else {
				if (modul.isBestanden()){
					modulView.setStyle("-fx-background-color : #c6f1e5;");
				} else {
					modulView.setStyle("-fx-background-color : #b2c0f6;");
				}
			}
			modulViewsListe.get(modulMap.get(k).getVerschobenesFachsemester().getid()).put(modulMap.get(k).getId(), modulView);
		}

		for(int x = 1; x <= semesteranzahl; x++){
			Fachsemester fachsemester;
			if(x%2 == 0){
				fachsemester = new Fachsemester(x, AngebotsIntervall.SOMMER);		
			}
			else{
				fachsemester = new Fachsemester(x, AngebotsIntervall.WINTER);			
			}
			Studiensemester studiensemester;
			studiensemester = modulService.getStudienplan().getSemesterMap().get(x);
			if(x > studienplanService.maxSemesterAnzahl()){
				Map<Integer, ModulView> modulViewMap = new HashMap<>();
				modulViewsListe.put(x, modulViewMap);
				studiensemester = new Studiensemester(this.app);
				modulService.getStudienplan().getSemesterMap().put(x, studiensemester);			
			}

			FlowPaneView paneView = new FlowPaneView(app, fachsemester, modulViewsListe.get(x), modulViewsListe, studiensemester);
			paneView.getStyleClass().add("flow-pane");
			flowPaneMap.put(x, paneView);

			
		}		
	}

	/**
	 * Initialisierung des Buttons mit einem Click Handler, der dafür sorgt, dass die Views neu geladen werden
	 */
	public void initButton(){
		addSemester.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			System.out.println(studienplanService.addSemesterAnzahl());
			ladeViewsNew(studienplanService.addSemesterAnzahl());
		});
	}

	/**
	 * Initialisiert Handler für das DragOver und DragDropped auf jeder FlowPaneView
	 */
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
						aktuellesFachsemester = flowPaneMap.get(k).getSemester();

						if(vorherigesFachsemester.getid() != aktuellesFachsemester.getid()){
							System.out.println("vorher: " + vorherigesFachsemester.getid() + "nachher" + aktuellesFachsemester.getid());


							
							modul.setVorherigesFachsemester(vorherigesFachsemester);
							modul.setVerschobenesFachsemester(aktuellesFachsemester);
							
							modulService.getStudienplan().holeStudiensemesterMitId(modul.getVerschobenesFachsemester().getid()).addToSemester(modul);
							modulService.getStudienplan().holeStudiensemesterMitId(modul.getVorherigesFachsemester().getid()).removeFromSemester(modul);
							studienplanService.checkAll();							

						}

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
