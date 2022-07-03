package de.hsrm.mi.swt.UI.StudentUI.Views;

import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.ModulService;
import de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung.ErrorService;
import de.hsrm.mi.swt.main.App;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

/**
 * Die StudentView beinhaltet die StudienplanungView und die ÜbersichtsView
 * @author Marie Bohnert, Beate Arnold, Arthur Fieguth
 */
public class StudentView extends BorderPane {

    private StudienplanungView studienplanungView;
    private UebersichtView uebersichtView;
    private ModulService modulService;
	private App app;
    private FlowPane errorPane;
    private Text errorText;
    private ErrorService errorService;
    private ErrorView errorView;


    /**
     * Konstruktor der StudentView
     * @param app
     * @param studienplanPfad : bekommt den Pfad des Studienplans mitgegeben
     */
    public StudentView(App app, String studienplanPfad){
        this.app = app;
        this.modulService = app.getModulService();
        this.errorService = app.getErrorService();

        errorView = new ErrorView(this.app);

        if(studienplanPfad != ""){
            modulService.erzeugen(studienplanPfad);
        }

		this.getStylesheets().add("style.css");
        this.getStyleClass().add("body");
        studienplanungView = new StudienplanungView(app);
        uebersichtView = new UebersichtView(app);

        this.setTop(errorView);
        this.setLeft(studienplanungView);
        this.setRight(uebersichtView);

    }

}
