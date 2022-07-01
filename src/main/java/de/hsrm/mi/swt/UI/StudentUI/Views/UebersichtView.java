package de.hsrm.mi.swt.UI.StudentUI.Views;

import de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung.StudienplanService;
import de.hsrm.mi.swt.main.App;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class UebersichtView extends BorderPane{

    private Button saveButton;
    private StudienplanService studienplanService;

    private App app;
    private Text headline;
    private VBox text;
    private Text studienfortschritt;
    private SimpleIntegerProperty actCP;

    public UebersichtView(App app){
        this.getStylesheets().add("style.css");
        this.getStyleClass().add("body");
        this.app = app;
        headline = new Text("Dein Studienfortschritt: ");
        studienplanService = app.getStudienplanService();
        saveButton = new Button("save");
        
        
        int maxCP = studienplanService.calcMaxCP();
        actCP = new SimpleIntegerProperty(studienplanService.calcActCP());
        
        
        studienfortschritt = new Text(actCP.getValue() + " CPs von " + maxCP + " CPs erreicht.");
        text = new VBox(headline, studienfortschritt);


        this.setTop(text);
        this.setBottom(saveButton);
        initialize();
    }

    public void initialize(){
        saveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                actCP.set(studienplanService.calcActCP());
                studienplanService.speicherePlan();            
		});
        actCP.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                actCP.set(studienplanService.calcActCP());
                System.out.println("Changed to " + newValue);
            }
    });
    }
    
}
