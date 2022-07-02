package de.hsrm.mi.swt.UI.StudentUI.Views;

import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.ModulService;
import de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung.ErrorService;
import de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung.StudienplanService;
import de.hsrm.mi.swt.main.App;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

public class StudentView extends BorderPane {

    private StudienplanungView studienplanungView;
    private UebersichtView uebersichtView;
    private ModulService modulService;
	private App app;
    private FlowPane errorPane;
    private Text errorText;
    private ErrorService errorService;
    private ErrorView errorView;

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
        this.setRight(studienplanungView);
        this.setLeft(uebersichtView);


    }


}
