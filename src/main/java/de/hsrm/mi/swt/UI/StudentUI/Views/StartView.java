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
        modulService = new ModulService();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML Dateien (*.xml)", "*.xml");
        // set selected filter

        //Erstelle Upload Button und FileChooser
        uploadButton = new Button("Upload Curriculum XML");
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(extFilter);

        this.setCenter(uploadButton);

        initialize();
    }

    private void initialize() {

		uploadButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            fileChooser.setTitle("Wählen Sie die Curriculum Datei in XML-Format aus...");
            File file = fileChooser.showOpenDialog(this.app.getPrimaryStage());
            this.app.switchView("STUDENTVIEW");            
            System.out.println(file.getAbsolutePath());
            modulService.erzeugen(file.getAbsolutePath());
            System.out.println(modulService.getModulMap());
		});

    }
    
}
