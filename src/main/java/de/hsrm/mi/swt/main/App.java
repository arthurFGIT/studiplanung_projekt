package de.hsrm.mi.swt.main;

import java.io.File;
import java.util.HashMap;

import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.ModulService;
import de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung.CheckService;
import de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung.ErrorService;
import de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung.StudienplanService;
import de.hsrm.mi.swt.UI.StudentUI.Views.StartView;
import de.hsrm.mi.swt.UI.StudentUI.Views.StudentView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {

	private StartView startView;
	private StudentView studentView;
	private Pane root;

	private HashMap<String, Pane> scenes;
	private Stage primaryStage;
	private boolean erstAnwendung = false; //TODO: verwenden

	private ModulService modulService;
	private CheckService checkService;
	private StudienplanService studienplanService;
	private ErrorService errorService;
	private String studienplanPfad = "";


	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.modulService = new ModulService(this);
		this.errorService = new ErrorService();
		this.checkService = new CheckService(modulService, errorService);
		this.studienplanService = new StudienplanService(modulService, checkService, errorService);
		
		this.scenes = new HashMap<>();
		startView = new StartView(this);

		
		try{
			
			scenes.put("StartView", startView);
			
			root = scenes.get("StartView");

			if (new File("./src/main/resources/individualPlan.xml").exists()) {
				studienplanPfad = "./src/main/resources/individualPlan.xml";
				studentView = new StudentView(this, studienplanPfad);
				scenes.put("StudentView", studentView);
				root = scenes.get("StudentView");  
			}
			
			Scene scene = new Scene(root, 1600, 768);

			primaryStage.setScene(scene);
			primaryStage.setTitle("Studiplanung");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void switchView(String name) {
		
		name = name.toUpperCase();
		
		switch(name) {
		case "STARTVIEW": 
			root = scenes.get("StartView");
			this.primaryStage.getScene().setRoot(root);
			break;
		case "STUDENTVIEW":
			studienplanPfad = "";
			studentView = new StudentView(this, studienplanPfad);
			scenes.put("StudentView", studentView);
			root = scenes.get("StudentView");
			this.primaryStage.getScene().setRoot(root);
			break;
		}
	}

	public Stage getPrimaryStage(){
		return this.primaryStage;
	}

	public ModulService getModulService() {
		return modulService;
	}

	public CheckService getCheckService() {
		return checkService;
	}

	public StudienplanService getStudienplanService() {
		return studienplanService;
	}

	public ErrorService getErrorService() {
		return errorService;
	}

	public void setModulService(ModulService modulService) {
		this.modulService = modulService;
	}

	public void setStudienplanService(StudienplanService studienplanService) {
		this.studienplanService = studienplanService;
	}

	

	
}
