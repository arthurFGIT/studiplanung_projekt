package de.hsrm.mi.swt.UI.StudentUI.Views;

import de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung.StudienplanService;
import de.hsrm.mi.swt.main.App;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class UebersichtView extends BorderPane{

    private Button saveButton;
    private StudienplanService studienplanService;

    private App app;

    public UebersichtView(App app){

        this.app = app;
        
        studienplanService = app.getStudienplanService();
        saveButton = new Button("save");
        
        this.setBottom(saveButton);
        initialize();
    }

    public void initialize(){
        saveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                studienplanService.speicherePlan();            
		});
    }
    
}
