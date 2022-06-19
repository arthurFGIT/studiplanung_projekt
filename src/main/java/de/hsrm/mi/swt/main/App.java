package de.hsrm.mi.swt.main;

import java.util.HashMap;

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


	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.scenes = new HashMap<>();
		startView = new StartView(this);
		studentView = new StudentView(this);
		try{
			
			scenes.put("StartView", startView);
			scenes.put("StudentView", studentView);

			root = scenes.get("StartView");
			Scene scene = new Scene(root, 500, 500);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

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
			root = scenes.get("StudentView");
			this.primaryStage.getScene().setRoot(root);
			break;
		}
	}

	public Stage getPrimaryStage(){
		return this.primaryStage;
	}
}
