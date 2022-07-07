package de.hsrm.mi.swt.UI.StudentUI.Views;

import de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung.StudienplanService;
import de.hsrm.mi.swt.main.App;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
/**
 * UebersichtsView, beinhaltet den Studienfortschritt und einen Speichern button für den geänderten Studienplan
 * @author Marie Bohnert, Beate Arnold, Arthur Fieguth
 */
public class UebersichtView extends BorderPane{

    private Button saveButton;

    private StudienplanService studienplanService;

    private App app;
    private Text headline;
    private VBox text;
    private Text studienfortschritt;
    private IntegerProperty actCP;
    private int maxCP;

    /**
     * Erstellt die View mit Text und Button
     * @param app
     */
    public UebersichtView(App app){
        this.getStylesheets().add("style.css");
        this.getStyleClass().add("body");
        this.app = app;
        headline = new Text("Dein Studienfortschritt: ");
        headline.getStyleClass().add("headline-text");
        studienplanService = app.getStudienplanService();
        saveButton = new Button("Plan speichern");   
        saveButton.getStyleClass().add("save-button");
           
        maxCP = studienplanService.calcMaxCP();
        actCP = studienplanService.calcActCP();
        actCP = studienplanService.getPropertyCP();
        studienfortschritt = new Text(actCP.getValue() + " CPs von " + maxCP + " CPs erreicht.");
        studienfortschritt.getStyleClass().add("headline-text");
        text = new VBox(headline, studienfortschritt);

        this.setTop(text);
        this.setBottom(saveButton);
        initialize();
    }

    /**
     * Initialisiert Handler für den speichern Button und einen Listener für die aktuelle Anzeige der bereits erreichten CPs
     */
    public void initialize(){

        saveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {                
                studienplanService.speicherePlan();            
		});  
        
        actCP.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                actCP.setValue(newValue);
                studienfortschritt = new Text(studienplanService.getPropertyCP().getValue() + " CPs von " + maxCP + " CPs erreicht.");
                studienfortschritt.getStyleClass().add("headline-text");
                text.getChildren().clear();
                text.getChildren().addAll(headline, studienfortschritt);
            }
        });
    }    
}
