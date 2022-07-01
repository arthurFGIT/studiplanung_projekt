package de.hsrm.mi.swt.UI.StudentUI.Views;

import java.io.File;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.ModulService;
import de.hsrm.mi.swt.main.App;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

public class StartView extends BorderPane{

    private ModulService modulService;

    private Button uploadButton;

    private FileChooser fileChooser;
    private App app;

    //TODO: Nur bei erfolgreichem Upload View wechseln

    public StartView(App app){
        this.app = app;
        modulService = app.getModulService();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML Dateien (*.xml)", "*.xml");
        // set selected filter

        //Erstelle Upload Button und FileChooser
        uploadButton = new Button("Klicken, um Curriculum (XML) hochzuladen");
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(extFilter);

        this.getStylesheets().add("style.css");
        this.getStyleClass().add("body");
        uploadButton.getStyleClass().add("upload-button");

        this.setCenter(uploadButton);

        initialize();
    }

    private void initialize() {

		uploadButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            fileChooser.setTitle("Wählen Sie die Curriculum Datei in XML-Format aus...");
            File file = fileChooser.showOpenDialog(this.app.getPrimaryStage());
            if(file != null){
              String pfad = file.getAbsolutePath();
              if(pfad != null){
                modulService.erzeugen(file.getAbsolutePath());
                this.app.switchView("STUDENTVIEW");            
              }
            }
		});

    }
    
}
