package de.hsrm.mi.swt.UI.StudentUI.Views;


import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Modul;
import de.hsrm.mi.swt.main.App;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ModulView extends VBox{

	public static final double MODULBREITE = 100.0;
	public static final double MODULHOEHE = 50.0;
	private Modul modul;
	private Text name;
	private Text cpGesamt;
	private App app;

	
	public ModulView(Modul m, App app){
		super();
		this.modul = m;
		this.app = app;

		System.out.println("ModulView");
        prefWidth(MODULBREITE);
        prefHeight(MODULHOEHE);
		setStyle("-fx-background-color: green");
		name = new Text(m.getName());
		cpGesamt = new Text("CP: " + String.valueOf(m.getCpGesamt()));
		
		this.getChildren().addAll(name, cpGesamt);	
	}

	public Modul getModul() {
		return this.modul;
	}

	public void setModul(Modul modul) {
		this.modul = modul;
	}
    
}






