package de.hsrm.mi.swt.UI.StudentUI.Views;

import java.io.File;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.ModulService;
import de.hsrm.mi.swt.main.App;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
/**
 * StartView, die das einladen das Standard Curriculum als XML Datei zulässt
 * @author Marie Bohnert, Beate Arnold, Arthur Fieguth
 */
public class StartView extends BorderPane{

    private ModulService modulService;

    private Button uploadButton;

    private FileChooser fileChooser;
    private App app;

    /**
     * Konstruktor für die StartView
     * @param app
     */
    public StartView(App app){
        this.app = app;
        modulService = app.getModulService();

        // set upload filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML Dateien (*.xml)", "*.xml");

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

    /**
     * Initialisiert den Handler für den Upload Button. Dieser öffnet die Uploadmöglichkeit
     */
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
