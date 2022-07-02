package de.hsrm.mi.swt.UI.StudentUI.Views;

import de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung.StudienplanService;
import de.hsrm.mi.swt.main.App;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.beans.property.IntegerProperty;

public class UebersichtView extends BorderPane{

    private Button saveButton;
    private StudienplanService studienplanService;

    private App app;
    private Text headline;
    private VBox text;
    private Text studienfortschritt;
    private IntegerProperty actCP;
    private int maxCP;

    public UebersichtView(App app){
        this.getStylesheets().add("style.css");
        this.getStyleClass().add("body");
        this.app = app;
        headline = new Text("Dein Studienfortschritt: ");
        studienplanService = app.getStudienplanService();
        saveButton = new Button("save");        
           
        maxCP = studienplanService.calcMaxCP();
        System.out.println("Max CP: " + maxCP);
        actCP = studienplanService.getPropertyCP();
        studienfortschritt = new Text(studienplanService.getPropertyCP().getValue() + " CPs von " + maxCP + " CPs erreicht.");
        text = new VBox(headline, studienfortschritt);

        this.setTop(text);
        this.setBottom(saveButton);
        initialize();
    }

    public void initialize(){

        saveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {                
                studienplanService.speicherePlan();            
		});
        
        actCP.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                // actCP.set(studienplanService.calcActCP());
                actCP.setValue(newValue);
                System.out.println("Changed to " + newValue);
                studienfortschritt = new Text(studienplanService.getPropertyCP().getValue() + " CPs von " + maxCP + " CPs erreicht.");
                text.getChildren().clear();
                text.getChildren().addAll(headline, studienfortschritt);
            }
        });
    }
    
}
